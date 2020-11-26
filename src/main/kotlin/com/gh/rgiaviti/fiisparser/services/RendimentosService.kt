package com.gh.rgiaviti.fiisparser.services

import com.gh.rgiaviti.fiisparser.data.domains.FundoImobiliario
import com.gh.rgiaviti.fiisparser.data.domains.Rendimento
import com.gh.rgiaviti.fiisparser.data.repositories.RendimentoRepository
import com.gh.rgiaviti.fiisparser.services.htmlparsers.RendimentoDTO
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class RendimentosService(private val rendimentoRepository: RendimentoRepository) {

    companion object {
        private val log by lazy { KotlinLogging.logger {} }
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    fun inserirRendimentos(fundoImobiliario: FundoImobiliario, rendimentos: List<RendimentoDTO>) {
        rendimentos
                .stream()
                .map { toRendimento(fundoImobiliario, it) }
                .filter { this.rendimentoAindaNaoInserido(fundoImobiliario, it.dataBase) }
                .forEach {
                    log.info(" :: [{}] Inserindo Rendimento {} - {}% - R$ {}", fundoImobiliario.ticker, it.dataBase, it.yield, it.rendimento)
                    this.rendimentoRepository.save(it)
                }
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    fun rendimentoAindaNaoInserido(fundoImobiliario: FundoImobiliario, dataBase: LocalDate): Boolean {
        return this.rendimentoRepository.rendimentoInserido(fundoImobiliario.id!!, dataBase).isEmpty
    }

    private fun toRendimento(fundoImobiliario: FundoImobiliario, rendimentoDTO: RendimentoDTO): Rendimento {
        return Rendimento(
                null,
                fundoImobiliario,
                rendimentoDTO.dataBase,
                rendimentoDTO.dataPagamento,
                rendimentoDTO.cotacaoBase,
                rendimentoDTO.yield,
                rendimentoDTO.rendimento,
                LocalDateTime.now()
        )
    }

}