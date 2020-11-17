package com.gh.rgiaviti.fiisparser

import com.gh.rgiaviti.fiisparser.services.FundoImobiliarioService
import com.gh.rgiaviti.fiisparser.services.HTMLParserService
import mu.KotlinLogging
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class ParserRunner(
        private val htmlParserService: HTMLParserService,
        private val fundoImobiliarioService: FundoImobiliarioService
) : CommandLineRunner {

    companion object {
        private val log by lazy { KotlinLogging.logger {} }
    }

    override fun run(vararg args: String?) {
        val extrairRendimentos = htmlParserService.extrairRendimentos("irdm11")
        print(extrairRendimentos)
    }
}