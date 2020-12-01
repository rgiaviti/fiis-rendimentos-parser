package com.gh.rag.invest.fiis.scrappers.rest.fiis.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class B3FundoImobiliarioDetalhe {

  @JsonProperty("cnpj")
  private String cnpj;

  @JsonProperty("tradingCode")
  private String ticker;

  @JsonProperty("codes")
  private List<String> codigos;
}
