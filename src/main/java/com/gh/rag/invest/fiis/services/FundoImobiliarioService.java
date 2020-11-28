package com.gh.rag.invest.fiis.services;

import javax.enterprise.context.ApplicationScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class FundoImobiliarioService {

  private static final Logger log = LoggerFactory.getLogger(FundoImobiliarioService.class);

  public void sayHello(final String fii) {
    log.info("Olá Mundo Quarkus + Picocli");
    log.info("FII passado -> {}", fii);
  }
}
