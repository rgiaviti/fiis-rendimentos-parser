package com.gh.rag.invest.fiis.scrappers.rest.fiis.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class B3FundoImobiliario {

  @JsonProperty("segmento")
  private String segmento;

  @JsonProperty("acronym")
  private String codigo;

  @JsonProperty("fundName")
  private String nome;

  @JsonProperty("companyName")
  private String nomeCompleto;

  @JsonProperty("cnpj")
  private String cnpj;

}
