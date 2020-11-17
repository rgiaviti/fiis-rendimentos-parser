package com.gh.rgiaviti.fiisparser.data.domains

import javax.persistence.*

@Entity
@Table(name = "fundos_imobiliarios")
data class FundoImobiliario(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        val id: Int?,

        @Column(name = "ticker", nullable = false)
        val ticker: String,

        @Column(name = "nome", nullable = false)
        val nome: String,

        @Column(name = "ativo", nullable = false)
        val ativo: Boolean,

        @OneToMany(mappedBy = "fundoImobiliario", targetEntity = Rendimento::class)
        val rendimentos: List<Rendimento>
)