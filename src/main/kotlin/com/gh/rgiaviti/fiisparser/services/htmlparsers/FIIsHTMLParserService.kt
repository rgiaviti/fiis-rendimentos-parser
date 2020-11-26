package com.gh.rgiaviti.fiisparser.services.htmlparsers

import mu.KotlinLogging
import org.springframework.stereotype.Service


@Service
class FIIsHTMLParserService : AbstractHTMLParser() {

    companion object {
        private val log by lazy { KotlinLogging.logger {} }
        const val BASE_URL = "https://www.clubefii.com.br/fundo_imobiliario_lista"
        const val TABELA_FIIS_SELECTOR = "#fundos_listados > table > tbody > tr"
        const val TD_A_SELECTOR = "td > a"
    }

    fun extrairFundosImobiliarios(): List<FundoImobiliarioDTO> {
        log.info(" :: Extraindo lista de FIIs em: {}", BASE_URL)
        val fundosImobiliarios = mutableListOf<FundoImobiliarioDTO>()
        val document = this.parseDocument(BASE_URL)

        val tabelaSelecionada = document.select(TABELA_FIIS_SELECTOR)
        tabelaSelecionada.forEach { linha ->
            if (linha.select(TD_A_SELECTOR).isNotEmpty()) {
                val ticker = linha.select(TD_A_SELECTOR)[0].text().trim() // ticker
                val nome = linha.select(TD_A_SELECTOR)[1].text().trim() // nome
                val segmento = linha.select(TD_A_SELECTOR)[5].text().trim() // segmento
                val administradora = linha.select(TD_A_SELECTOR)[6].text().trim() // administradora

                val valorCota: String
                val ativo: Boolean
                if (linha.select(TD_A_SELECTOR)[2].text() == "N/D") {
                    valorCota = linha.select(TD_A_SELECTOR)[2].text()
                    ativo = false
                } else {
                    valorCota = linha.select(TD_A_SELECTOR)[2].select("span")[0].text().trim() // valor cota
                    ativo = true
                }

                fundosImobiliarios.add(FundoImobiliarioDTO(ticker, nome, valorCota, segmento, administradora, ativo))
            }
        }

        return fundosImobiliarios
    }
}

data class FundoImobiliarioDTO(
        val ticker: String,
        val nome: String,
        val valorCota: String,
        val segmento: String,
        val administradora: String,
        val ativo: Boolean
)