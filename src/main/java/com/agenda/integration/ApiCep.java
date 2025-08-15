package com.agenda.integration;

import com.agenda.model.Endereco;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiCep {

  public Endereco buscaEnderecoPeloCEP(String cep) {
    try {
      URI enderecoUri = URI.create("https://viacep.com.br/ws/" + cep + "/json/");

      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder(enderecoUri).GET().build();

      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      String jsonBody = response.body();

      Gson gson = new Gson();
      return gson.fromJson(jsonBody, Endereco.class);

    } catch (Exception e) {
      System.err.println("Erro ao consultar o CEP: " + e.getMessage());
      return null;
    }
  }
}