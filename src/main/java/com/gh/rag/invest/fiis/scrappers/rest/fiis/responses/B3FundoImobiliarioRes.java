package com.gh.rag.invest.fiis.scrappers.rest.fiis.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class B3FundoImobiliarioRes {

  @JsonProperty("page")
  private B3Paginacao paginacao;

  @JsonProperty("results")
  private B3FundoImobiliario fundos;
}
