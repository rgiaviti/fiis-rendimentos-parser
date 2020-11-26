package com.gh.rgiaviti.fiisparser.data.domains

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "errors_log")
data class ErrorLog(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        val id: Int?,

        @Column(name = "datahora", nullable = false)
        val datahora: LocalDateTime,

        @Column(name = "mensagem", nullable = false)
        val mensagem: String,

        @Column(name = "detalhes", nullable = true)
        val detalhes: String?,
)