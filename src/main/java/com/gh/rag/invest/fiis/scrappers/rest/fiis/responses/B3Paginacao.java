package com.gh.rag.invest.fiis.scrappers.rest.fiis.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class B3Paginacao {

  @JsonProperty("pageNumber")
  private Integer pageNumber;

  @JsonProperty("pageSize")
  private Integer pageSize;

  @JsonProperty("totalRecords")
  private Integer totalRecords;

  @JsonProperty("totalPages")
  private Integer totalPages;
}
