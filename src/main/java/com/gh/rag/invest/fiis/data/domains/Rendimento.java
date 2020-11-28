package com.gh.rag.invest.fiis.data.domains;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rendimentos")
public class Rendimento extends PanacheEntity {

  @ManyToOne(cascade = {PERSIST, MERGE, REFRESH})
  @JoinColumn(name = "fii_id")
  public FundoImobiliario fundoImobiliario;

  @Column(name = "data_base", nullable = false)
  public LocalDate dataBase;

  @Column(name = "data_pagamento", nullable = false)
  public LocalDate dataPagamento;

  @Column(name = "cotacao_base", nullable = false)
  public BigDecimal cotacaoBase;

  @Column(name = "yield", nullable = false)
  public BigDecimal yield;

  @Column(name = "rendimento", nullable = false)
  public BigDecimal rendimento;

  @Column(name = "data_criacao", nullable = false)
  public LocalDateTime dataCriacao;
}
