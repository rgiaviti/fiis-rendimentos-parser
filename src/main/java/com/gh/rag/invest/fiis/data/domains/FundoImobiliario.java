package com.gh.rag.invest.fiis.data.domains;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fundos_imobiliarios")
public class FundoImobiliario extends PanacheEntity {

  @Column(name = "ticker", unique = true, nullable = false)
  public String ticker;

  @Column(name = "razao_social", nullable = false)
  public String razaoSocial;

  @Column(name = "ativo")
  public Boolean ativo;

  @Column(name = "cnpj", unique = true)
  public String cnpj;

  @ManyToOne(cascade = {PERSIST, MERGE, REFRESH})
  @JoinColumn(name = "segmento_id")
  public Segmento segmento;

  @ManyToOne(cascade = {PERSIST, MERGE, REFRESH})
  @JoinColumn(name = "administradora_id")
  public Administradora administradora;

  @Column(name = "observacoes")
  public String observacoes;
}
