package com.gh.rgiaviti.fiisparser

import com.gh.rgiaviti.fiisparser.data.domains.FundoImobiliario
import com.gh.rgiaviti.fiisparser.services.FundoImobiliarioService
import com.gh.rgiaviti.fiisparser.services.HTMLParserService
import com.gh.rgiaviti.fiisparser.services.RendimentosService
import mu.KotlinLogging
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class ParserRunner(
        private val htmlParserService: HTMLParserService,
        private val fundoImobiliarioService: FundoImobiliarioService,
        private val rendimentosService: RendimentosService
) : CommandLineRunner {

    companion object {
        private val log by lazy { KotlinLogging.logger {} }
    }

    override fun run(vararg args: String?) {
        if (args.isEmpty()) {
            this.sincronizarTodosAtivos()
        } else {
            this.sincronizarAtivosEspecificos(args.asList())
        }
    }

    fun sincronizarAtivosEspecificos(tickers: List<String?>) {
        tickers.forEach {
            this.fundoImobiliarioService.getByTicker(it!!).ifPresent { fii ->
                this.sincronizarAtivo(fii)
                waitForNextFii()
            }
        }
    }

    fun sincronizarTodosAtivos() {
        val fiis = this.fundoImobiliarioService.listarAtivos()

        fiis.parallelStream().forEach {
            this.sincronizarAtivo(it)
            waitForNextFii()
        }
    }

    fun sincronizarAtivo(fii: FundoImobiliario) {
        log.info(" :: Processando o Fundo Imobiliário: {}", fii.ticker)
        val rendimentos = htmlParserService.extrairRendimentos(fii.ticker)
        this.rendimentosService.inserirRendimentos(fii, rendimentos)
        log.info(" :: Finalizado o Processamento do Fundo Imobiliário: {}", fii.ticker)
    }

    private fun waitForNextFii() {
        val waitTime = (5..15).random().toLong()
        log.info(" :: Aguardando por $waitTime segundos até o próximo FII")
        Thread.sleep(TimeUnit.SECONDS.toMillis(waitTime))
    }
}