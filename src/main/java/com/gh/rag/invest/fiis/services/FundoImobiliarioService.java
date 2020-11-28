package com.gh.rag.invest.fiis.services;

import com.gh.rag.invest.fiis.data.domains.FundoImobiliario;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@ActivateRequestContext
public class FundoImobiliarioService {

  private static final Logger log = LoggerFactory.getLogger(FundoImobiliarioService.class);

  public void sayHello(final String fii) {
    log.info("Número de FIIs -> {}", FundoImobiliario.count());
  }
}
