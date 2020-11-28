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
@Table(name = "recomendacoes")
public class Recomendacao extends PanacheEntity {

  @ManyToOne(cascade = {PERSIST, MERGE, REFRESH})
  @JoinColumn(name = "fii_id")
  public FundoImobiliario fundoImobiliario;

  @ManyToOne(cascade = {PERSIST, MERGE, REFRESH})
  @JoinColumn(name = "casa_analise_id")
  public CasaAnalise casaAnalise;

  @Column(name = "data_inicio", nullable = false)
  public LocalDate dataInicio;

  @Column(name = "data_fim")
  public LocalDate dataFim;

  @Column(name = "alocacao", nullable = false)
  public BigDecimal alocacao;

  @Column(name = "yield_esperado", nullable = false)
  public BigDecimal yieldEsperado;

  @Column(name = "preco_teto", nullable = false)
  public BigDecimal precoTeto;

  @Column(name = "preco_entrada", nullable = false)
  public BigDecimal precoEntrada;

  @Column(name = "data_criacao", nullable = false)
  public LocalDateTime dataCriacao;

  @Column(name = "data_atualizacao")
  public LocalDateTime dataAtualizacao;
}
