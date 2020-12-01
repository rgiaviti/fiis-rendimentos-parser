package com.gh.rag.invest.fiis.business.services;

import com.gh.rag.invest.fiis.scrappers.rest.fiis.B3FiiRestParser;
import com.gh.rag.invest.fiis.scrappers.rest.fiis.responses.B3FundoImobiliarioRes;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@ActivateRequestContext
public class FundoImobiliarioService {

  private static final Logger log = LoggerFactory.getLogger(FundoImobiliarioService.class);

  @Inject
  @RestClient
  B3FiiRestParser b3FiiRestParser;

  public void sayHello(final String fii) {
    B3FundoImobiliarioRes listFundosImobiliarios = b3FiiRestParser.getListFundosImobiliarios();
    log.info(listFundosImobiliarios.getFundos().getCodigo());
  }
}
