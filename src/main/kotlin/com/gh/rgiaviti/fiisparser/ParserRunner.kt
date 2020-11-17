package com.gh.rgiaviti.fiisparser

import com.gh.rgiaviti.fiisparser.services.FundoImobiliarioService
import mu.KotlinLogging
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class ParserRunner(
        private val fundoImobiliarioService: FundoImobiliarioService
) : CommandLineRunner {

    companion object {
        private val log by lazy { KotlinLogging.logger {} }
    }

    override fun run(vararg args: String?) {
        fundoImobiliarioService.listarAtivos().forEach {
            log.info(it.rendimentos.toString())
        }
        log.info("rodou?")
    }
}