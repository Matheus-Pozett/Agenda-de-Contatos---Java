package com.agenda.services;

import com.agenda.model.Contato;
import com.agenda.persistence.GerenciadorArquivo;
import com.agenda.util.Validador;

import java.util.ArrayList;

public class Agenda {
  private ArrayList<Contato> contatos;
  private GerenciadorArquivo gerenciador;

  public Agenda() {
    this.gerenciador = new GerenciadorArquivo();
    this.contatos = gerenciador.carregarAgenda();
  }

  private boolean existeContato(String cpf) {
    return contatos.stream().anyMatch(c -> c.getCpf().equals(cpf));
  }

  public String criarContato(String cpf, String nome, String telefone) {
    if (existeContato(cpf)) {
      return "Contato já existe!";
    }

    if (Validador.validarCPF(cpf)) {
      Contato contato = new Contato(cpf, nome, telefone);
      contatos.add(contato);
      gerenciador.salvarAgenda(contatos);
      return "Contato criado com sucesso!";

    } else {
      return "Cpf inválido!";
    }
  }

  public String consultarContato(String cpf) {
    return contatos.stream()
            .filter(c -> c.getCpf().equals(cpf))
            .findFirst()
            .map(contato -> contato.toString())
            .orElse("Contato não existe");
  }

  public void excluirContato(String cpf) {
    boolean removerContato = contatos.removeIf(c -> c.getCpf().equals(cpf));

    if (removerContato) {
      gerenciador.salvarAgenda(contatos);
      System.out.println("Contato excluído com sucesso!");
    } else {
      System.out.println("Contato não existe");
    }
  }

  public void listarContatos() {
    for (Contato x : contatos) {
      System.out.println(x);
    }
  }


}
