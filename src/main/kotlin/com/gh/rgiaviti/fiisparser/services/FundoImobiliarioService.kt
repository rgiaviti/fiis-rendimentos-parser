package com.gh.rgiaviti.fiisparser.services

import com.gh.rgiaviti.fiisparser.data.domains.FundoImobiliario
import com.gh.rgiaviti.fiisparser.data.repositories.FundoImobiliarioRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class FundoImobiliarioService(
        private val fundoImobiliarioRepository: FundoImobiliarioRepository
) {

    companion object {
        private val log by lazy { KotlinLogging.logger {} }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    fun listarAtivos(): List<FundoImobiliario> {
        return fundoImobiliarioRepository.findAll()
    }
}