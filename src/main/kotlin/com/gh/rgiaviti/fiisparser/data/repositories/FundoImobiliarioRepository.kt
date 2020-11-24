package com.gh.rgiaviti.fiisparser.data.repositories

import com.gh.rgiaviti.fiisparser.data.domains.FundoImobiliario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface FundoImobiliarioRepository : JpaRepository<FundoImobiliario, Int> {

    @Query("SELECT fii FROM FundoImobiliario fii WHERE fii.ativo = 1 ORDER BY fii.nome")
    fun listarAtivos(): List<FundoImobiliario>

    @Query("SELECT fii FROM FundoImobiliario fii WHERE lower(fii.ticker) = lower(?1) AND fii.ativo = 1")
    fun getByTicker(ticker: String): Optional<FundoImobiliario>
}