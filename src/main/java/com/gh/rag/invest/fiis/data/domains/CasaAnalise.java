package com.gh.rag.invest.fiis.data.domains;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "casa_analises")
public class CasaAnalise extends PanacheEntity {

  @Column(name = "nome", nullable = false, unique = true)
  public String nome;

  @Column(name = "site")
  public String site;

  @Column(name = "data_criacao", nullable = false)
  public LocalDateTime dataCriacao;
}
