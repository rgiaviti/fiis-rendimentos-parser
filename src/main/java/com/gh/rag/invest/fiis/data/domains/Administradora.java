package com.gh.rag.invest.fiis.data.domains;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "administradoras")
public class Administradora extends PanacheEntity {

  @Column(name = "razao_social", nullable = false)
  public String razaoSocial;

  @Column(name = "cnpj", nullable = false)
  public String cnpj;
}
