package com.gh.rgiaviti.fiisparser.services.htmlparsers

import mu.KotlinLogging
import org.jsoup.HttpStatusException
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.math.BigDecimal
import java.net.SocketTimeoutException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.concurrent.atomic.AtomicInteger

abstract class AbstractHTMLParser {

    companion object {
        private val log by lazy { KotlinLogging.logger {} }
        const val USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36"
        const val MAX_TIMOUTS_TRIES = 5
        const val HTML_TR_SELECTOR = "tr"
        const val HTML_TD_SELECTOR = "td"
    }

    fun parseDocument(url: String): Document {
        val tentativasRequest = AtomicInteger(0)

        do {
            tentativasRequest.incrementAndGet()

            try {
                return Jsoup.connect(url)
                        .userAgent(USER_AGENT)
                        .followRedirects(true)
                        .ignoreHttpErrors(false)
                        .timeout(5000)
                        .get()
            } catch (ex: SocketTimeoutException) {
                log.warn(" :: Timeout no request para a url {}", url)
            } catch (ex: HttpStatusException) {
                log.error(" :: HTTP Status Error no Request para a url {}", url)
                log.error(" :: URL: {}", ex.url)
                log.error(" :: HTTP Status Code: {}", ex.statusCode)
                log.error(" :: Mensagem: {}", ex.message)
            } catch (ex: Exception) {
                log.error(" :: Erro Desconhecido no Request para a url {}", url)
                log.error(" :: Mensagem: {}", ex.message)
            }
        } while (tentativasRequest.get() < MAX_TIMOUTS_TRIES)

        log.error(" :: Máximo de tentativas atingido para url: {}", url)
        log.error(" :: Tentativas: {}/{}", tentativasRequest.get(), MAX_TIMOUTS_TRIES)
        log.error(" :: Interrompendo a execução")

        throw SocketTimeoutException()
    }

    protected fun toDouble(value: String) = value
            .replace(",", ".")
            .replace("%", "")
            .trim()
            .toDouble()

    protected fun toBigDecimal(value: String) = BigDecimal(value
            .replace(".", "")
            .replace(",", ".")
            .replace("R", "")
            .replace("$", "")
            .trim())

    protected fun toLocalDate(date: String, format: String): LocalDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(format))
}