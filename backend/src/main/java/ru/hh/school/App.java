package ru.hh.school;

import ru.hh.nab.starter.NabApplication;
import ru.hh.nab.starter.server.jetty.JettyServer;
import ru.hh.school.config.ProdConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class App {

  public static void main(String[] args) {
    NabApplication
            .builder()
            .configureJersey()
            .bindToRoot()
            .build()
            .run(ProdConfig.class);
  }
}
