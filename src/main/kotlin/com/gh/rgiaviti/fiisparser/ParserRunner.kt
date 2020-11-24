package com.gh.rgiaviti.fiisparser

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
        private const val waitTime = 10
    }

    override fun run(vararg args: String?) {
        val fiis = this.fundoImobiliarioService.listarAtivos()
        fiis.forEach {
            log.info(" :: Processando o Fundo Imobiliário: {}", it.ticker)
            val rendimentos = htmlParserService.extrairRendimentos(it.ticker)
            this.rendimentosService.inserirRendimentos(it, rendimentos)
            log.info(" :: Finalizado o Processamento do Fundo Imobiliário: {}", it.ticker)
            log.info(" :: Aguardando por $waitTime segundos até o próximo request")
            Thread.sleep(TimeUnit.SECONDS.toMillis(10))
        }
    }
}