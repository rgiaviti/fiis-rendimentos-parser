package com.gh.rag.invest.fiis;

import com.gh.rag.invest.fiis.services.FundoImobiliarioService;
import javax.inject.Inject;
import picocli.CommandLine;

@CommandLine.Command
public class AppStart implements Runnable {

  @CommandLine.Option(names = {"-n", "--name"}, description = "Who will we greet?", defaultValue = "World")
  private String name;

  @Inject
  FundoImobiliarioService fundoImobiliarioService;

  @Override
  public void run() {
    this.fundoImobiliarioService.sayHello(name);
  }
}
