package com.gh.rgiaviti.fiisparser.data.domains

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "rendimentos")
data class Rendimento(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        val id: Int,

        @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], targetEntity = FundoImobiliario::class)
        @JoinColumn(name = "fii_id", nullable = false)
        val fundoImobiliario: FundoImobiliario,

        @Column(name = "data_base", nullable = false)
        val dataBase: LocalDate,

        @Column(name = "data_pagamento", nullable = false)
        val dataPagamento: LocalDate,

        @Column(name = "cotacao_base", nullable = false)
        val cotacaoBase: BigDecimal,

        @Column(name = "yield", nullable = false)
        val yield: Double,

        @Column(name = "rendimento", nullable = false)
        val rendimento: BigDecimal,

        @Column(name = "data_criacao", nullable = false)
        val dataCriacao: LocalDateTime
)