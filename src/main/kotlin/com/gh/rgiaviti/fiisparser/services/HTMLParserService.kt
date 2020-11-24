package com.gh.rgiaviti.fiisparser.services

import com.gh.rgiaviti.fiisparser.services.Constantes.BASE_URL
import com.gh.rgiaviti.fiisparser.services.Constantes.DATE_FORMAT
import com.gh.rgiaviti.fiisparser.services.Constantes.HTML_TD
import com.gh.rgiaviti.fiisparser.services.Constantes.HTML_TR
import com.gh.rgiaviti.fiisparser.services.Constantes.MAX_TIMOUTS_TRIES
import com.gh.rgiaviti.fiisparser.services.Constantes.TABELA_RENDIMENTOS_SELECTOR
import com.gh.rgiaviti.fiisparser.services.Constantes.USER_AGENT
import mu.KotlinLogging
import org.jsoup.HttpStatusException
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.net.SocketTimeoutException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.concurrent.atomic.AtomicInteger

object Constantes {

    const val BASE_URL = "https://fiis.com.br/"
    const val TABELA_RENDIMENTOS_SELECTOR = "#last-revenues--table > tbody"
    const val HTML_TR = "tr"
    const val HTML_TD = "td"
    const val USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36"
    const val MAX_TIMOUTS_TRIES = 5
    const val DATE_FORMAT = "dd/MM/yy"
}

@Service
class HTMLParserService {

    companion object {
        private val log by lazy { KotlinLogging.logger {} }
    }

    fun extrairRendimentos(ticker: String): List<RendimentoDTO> {
        log.info(":: Extraindo Rendimentos para o Fundo Imobiliário: {}", ticker.toUpperCase())
        val rendimentos = mutableListOf<RendimentoDTO>()
        val document = this.getDocument(ticker)
        val tabelaSelecionada = document.select(TABELA_RENDIMENTOS_SELECTOR)

        tabelaSelecionada.select(HTML_TR).forEach { linha ->
            rendimentos.add(RendimentoDTO(
                    toLocalDate(linha.select(HTML_TD)[0].text()), // Data Base
                    toLocalDate(linha.select(HTML_TD)[1].text()), // Data Pagamento
                    toBigDecimal(linha.select(HTML_TD)[2].text()), // Cotação Base
                    toDouble(linha.select(HTML_TD)[3].text()), // DY
                    toBigDecimal(linha.select(HTML_TD)[4].text()) // Rendimento
            ))
        }

        return rendimentos
    }

    private fun getDocument(ticker: String): Document {
        val tentativasRequest = AtomicInteger(0)

        do {
            tentativasRequest.incrementAndGet()
            try {
                return Jsoup.connect(BASE_URL.plus(ticker))
                        .userAgent(USER_AGENT)
                        .followRedirects(true)
                        .ignoreHttpErrors(false)
                        .timeout(5000)
                        .get()
            } catch (ex: SocketTimeoutException) {
                log.warn(":: Timeout no request para o ticker {}", ticker)
            } catch (ex: HttpStatusException) {
                log.error(":: HTTP Status Error no Request para o ticker {}", ticker)
                log.error(":: URL: {}", ex.url)
                log.error(":: HTTP Status Code: {}", ex.statusCode)
                log.error(":: Mensagem: {}", ex.message)
            } catch (ex: Exception) {
                log.error(":: Erro Desconhecido no Request para o ticker {}", ticker)
                log.error(":: Mensagem: {}", ex.message)
            }
        } while (tentativasRequest.get() < MAX_TIMOUTS_TRIES)

        log.error("Máximo de tentativas atingido para -> {}", ticker)
        log.error("Tentativas: {}/{}", tentativasRequest.get(), MAX_TIMOUTS_TRIES)
        log.error("Interrompendo a execução")

        throw SocketTimeoutException()
    }

    private fun toDouble(value: String) = value
            .replace(",", ".")
            .replace("%", "")
            .trim()
            .toDouble()

    private fun toBigDecimal(value: String) = BigDecimal(value
            .replace(",", ".")
            .replace("R", "")
            .replace("$", "")
            .trim())

    private fun toLocalDate(date: String) = LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT))
}

data class RendimentoDTO(
        val dataBase: LocalDate,
        val dataPagamento: LocalDate,
        val cotacaoBase: BigDecimal,
        val yield: Double,
        val rendimento: BigDecimal
)