package com.gh.rag.invest.fiis.data.domains;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "segmentos")
public class Segmento extends PanacheEntity {

  @Column(name = "tipo", nullable = false)
  public String tipo;

  @Column(name = "descricao")
  public String descricao;
}
