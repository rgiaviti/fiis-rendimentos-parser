package com.gh.rgiaviti.fiisparser.data.repositories

import com.gh.rgiaviti.fiisparser.data.domains.Rendimento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*

@Repository
interface RendimentoRepository : JpaRepository<Rendimento, Int> {

    @Query("SELECT r FROM Rendimento r WHERE r.fundoImobiliario.id = ?1 AND r.dataBase = ?2")
    fun rendimentoInserido(fundoImobiliarioId: Int, dataBase: LocalDate): Optional<Rendimento>

}