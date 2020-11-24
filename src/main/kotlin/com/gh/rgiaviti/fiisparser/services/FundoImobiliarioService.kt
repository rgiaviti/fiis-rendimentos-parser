package com.gh.rgiaviti.fiisparser.services

import com.gh.rgiaviti.fiisparser.data.domains.FundoImobiliario
import com.gh.rgiaviti.fiisparser.data.repositories.FundoImobiliarioRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class FundoImobiliarioService(
        private val fundoImobiliarioRepository: FundoImobiliarioRepository
) {

    @Transactional(propagation = Propagation.SUPPORTS)
    fun listarAtivos(): List<FundoImobiliario> {
        return fundoImobiliarioRepository.listarAtivos()
    }

    @Transactional
    fun getByTicker(ticker: String): Optional<FundoImobiliario> {
        return this.fundoImobiliarioRepository.getByTicker(ticker)
    }
}