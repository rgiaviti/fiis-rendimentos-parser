package com.gh.rgiaviti.fiisparser.services

import com.gh.rgiaviti.fiisparser.data.domains.FundoImobiliario
import com.gh.rgiaviti.fiisparser.data.repositories.FundoImobiliarioRepository
import com.gh.rgiaviti.fiisparser.services.htmlparsers.FundoImobiliarioDTO
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.util.*
import java.util.stream.Collectors

@Service
class FundoImobiliarioService(
        private val fundoImobiliarioRepository: FundoImobiliarioRepository
) {

    companion object {
        private val log by lazy { KotlinLogging.logger {} }
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    fun listarAtivos(): List<FundoImobiliario> {
        return fundoImobiliarioRepository.listarAtivos()
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    fun getByTicker(ticker: String): Optional<FundoImobiliario> {
        return this.fundoImobiliarioRepository.getByTicker(ticker)
    }

    @Transactional(propagation = Propagation.REQUIRED)
    fun sincronizarFundosImobiliarios(fundosImobiliariosDTO: List<FundoImobiliarioDTO>) {
        val fiisCadastrados = this.fundoImobiliarioRepository.findAll()
        val novosFiis = fundosImobiliariosDTO.stream()
                .filter { fiiDto -> fiisCadastrados.stream().noneMatch { fiiDb -> fiiDb.ticker == fiiDto.ticker } }
                .map { fiiDto -> FundoImobiliario(null, fiiDto.ticker, fiiDto.nome, fiiDto.ativo, emptyList()) }
                .collect(Collectors.toList())

        novosFiis.forEach { log.info(" :: Cadastrando Fundo Novo com Ticker {}", it.ticker) }

        this.fundoImobiliarioRepository.saveAll(novosFiis)
    }
}