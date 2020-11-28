package com.gh.rag.invest.fiis;

import com.gh.rag.invest.fiis.services.FundoImobiliarioService;
import lombok.RequiredArgsConstructor;
import picocli.CommandLine;

@CommandLine.Command
@RequiredArgsConstructor
public class AppStart implements Runnable {

  @CommandLine.Option(names = {"-n", "--name"}, description = "Who will we greet?", defaultValue = "World")
  private String name;

  private final FundoImobiliarioService fundoImobiliarioService;

  @Override
  public void run() {
    this.fundoImobiliarioService.sayHello(name);
  }
}
