package com.gh.rag.invest.fiis.data.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;

@Builder
public class RendimentoDTO {

  private LocalDate dataBase;
  private LocalDate dataPagamento;
  private BigDecimal cotacaoBase;
  private BigDecimal yield;
  private BigDecimal rendimento;
}
