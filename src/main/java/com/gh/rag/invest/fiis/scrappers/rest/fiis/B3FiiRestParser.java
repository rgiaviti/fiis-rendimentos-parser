package com.gh.rag.invest.fiis.scrappers.rest.fiis;

import com.gh.rag.invest.fiis.scrappers.rest.fiis.responses.B3FundoImobiliarioRes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "https://sistemaswebb3-listados.b3.com.br/fundsProxy/fundsCall")
public interface B3FiiRestParser {

  @GET
  @Path("/GetListedFundsSIG/eyJ0eXBlRnVuZCI6NywicGFnZU51bWJlciI6MSwicGFnZVNpemUiOjUwMCwia2V5d29yZCI6IiJ9")
  @Produces("application/json")
  B3FundoImobiliarioRes getListFundosImobiliarios();
}
