package com.gh.rgiaviti.fiisparser.services

import com.gh.rgiaviti.fiisparser.data.domains.ErrorLog
import com.gh.rgiaviti.fiisparser.data.repositories.ErrorLogRepository
import mu.KotlinLogging
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Aspect
class ErrorLogService(
        private val errorLogRepository: ErrorLogRepository
) {

    companion object {
        private val log by lazy { KotlinLogging.logger {} }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    fun save(error: ErrorLog) {
        this.errorLogRepository.save(error)
    }

    @AfterThrowing(pointcut = "execution(* com.gh.rgiaviti.fiisparser.*.*.*.* (..))", throwing = "ex")
    fun captureException(ex: Exception) {
        log.info(":: Erro capturado. Salvando em banco de dados para análise")
        this.save(ErrorLog(null, LocalDateTime.now(), ex.localizedMessage, ex.stackTraceToString()))
    }
}