package com.gh.rgiaviti.fiisparser.services.htmlparsers

import mu.KotlinLogging
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate

@Service
class RendimentosHTMLParserService : AbstractHTMLParser() {

    companion object {
        private val log by lazy { KotlinLogging.logger {} }

        const val BASE_URL = "https://fiis.com.br/"
        const val TABELA_RENDIMENTOS_SELECTOR = "#last-revenues--table > tbody"
        const val DATE_FORMAT = "dd/MM/yy"
    }

    fun extrairRendimentos(ticker: String): List<RendimentoDTO> {
        log.info(" :: Extraindo Rendimentos para o Fundo Imobiliário: {}", ticker.toUpperCase())
        val rendimentos = mutableListOf<RendimentoDTO>()
        val document = this.parseDocument(BASE_URL.plus(ticker))
        val tabelaSelecionada = document.select(TABELA_RENDIMENTOS_SELECTOR)

        tabelaSelecionada.select(HTML_TR_SELECTOR).forEach { linha ->
            rendimentos.add(RendimentoDTO(
                    toLocalDate(linha.select(HTML_TD_SELECTOR)[0].text(), DATE_FORMAT), // Data Base
                    toLocalDate(linha.select(HTML_TD_SELECTOR)[1].text(), DATE_FORMAT), // Data Pagamento
                    toBigDecimal(linha.select(HTML_TD_SELECTOR)[2].text()), // Cotação Base
                    toDouble(linha.select(HTML_TD_SELECTOR)[3].text()), // DY
                    toBigDecimal(linha.select(HTML_TD_SELECTOR)[4].text()) // Rendimento
            ))
        }

        return rendimentos
    }
}

data class RendimentoDTO(
        val dataBase: LocalDate,
        val dataPagamento: LocalDate,
        val cotacaoBase: BigDecimal,
        val yield: Double,
        val rendimento: BigDecimal
)