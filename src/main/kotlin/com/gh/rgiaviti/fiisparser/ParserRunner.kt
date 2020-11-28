package com.gh.rgiaviti.fiisparser

import com.gh.rgiaviti.fiisparser.data.domains.FundoImobiliario
import com.gh.rgiaviti.fiisparser.services.FundoImobiliarioService
import com.gh.rgiaviti.fiisparser.services.RendimentosService
import com.gh.rgiaviti.fiisparser.services.htmlparsers.FIIsHTMLParserService
import com.gh.rgiaviti.fiisparser.services.htmlparsers.RendimentosHTMLParserService
import mu.KotlinLogging
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class ParserRunner(
        private val rendimentosHTMLParserService: RendimentosHTMLParserService,
        private val fiisHTMLParserService: FIIsHTMLParserService,
        private val fundoImobiliarioService: FundoImobiliarioService,
        private val rendimentosService: RendimentosService
) : CommandLineRunner {

    companion object {
        private val log by lazy { KotlinLogging.logger {} }
    }

    override fun run(vararg args: String?) {
        //this.sincronizarFundosImobiliarios()

        if (args.isEmpty()) {
            this.sincronizarTodosRendimentos()
        } else {
            this.sincronizarRendimentosEspecificos(args.asList())
        }
    }

    fun sincronizarFundosImobiliarios() {
        val fiisAtualizados = this.fiisHTMLParserService.extrairFundosImobiliarios()
        this.fundoImobiliarioService.sincronizarFundosImobiliarios(fiisAtualizados)
    }

    fun sincronizarRendimentosEspecificos(tickers: List<String?>) {
        tickers.forEach {
            this.fundoImobiliarioService.getByTicker(it!!).ifPresent { fii ->
                this.sincronizarRendimento(fii)
                waitForNextFii()
            }
        }
    }

    fun sincronizarTodosRendimentos() {
        val fiis = this.fundoImobiliarioService.listarAtivos()

        fiis.parallelStream().forEach {
            this.sincronizarRendimento(it)
            waitForNextFii()
        }
    }

    private fun sincronizarRendimento(fii: FundoImobiliario) {
        log.info(" :: Processando o Fundo Imobiliário: {}", fii.ticker)
        val rendimentos = this.rendimentosHTMLParserService.extrairRendimentos(fii.ticker)
        this.rendimentosService.inserirRendimentos(fii, rendimentos)
        log.info(" :: Finalizado o Processamento do Fundo Imobiliário: {}", fii.ticker)
    }

    private fun waitForNextFii() {
        val waitTime = (3..7).random().toLong()
        log.info(" :: Aguardando por $waitTime segundos até o próximo FII")
        Thread.sleep(TimeUnit.SECONDS.toMillis(waitTime))
    }
}