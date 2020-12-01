package com.gh.rag.invest.fiis.data.dtos;

import lombok.Builder;

@Builder
public class FundoImobiliarioDTO {
  private String ticker;
  private String razaoSocial;
  private String cnpj;
  private String segmento;
  private String administradora;
}
