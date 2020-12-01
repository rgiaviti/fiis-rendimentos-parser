package com.gh.rag.invest.fiis.data.domains;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "configuracoes")
public class Configuracao extends PanacheEntity {

  @Column(name = "nome", unique = true, nullable = false)
  public String nome;

  @Column(name = "valor", nullable = false)
  public String valor;

  @Column(name = "descricao")
  public String descricao;
}
