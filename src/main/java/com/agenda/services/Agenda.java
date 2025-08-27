package com.agenda.services;

import com.agenda.integration.ApiCep;
import com.agenda.model.Contato;
import com.agenda.model.Endereco;
import com.agenda.persistence.GerenciadorArquivo;
import com.agenda.util.Validador;

import java.util.ArrayList;
import java.util.Optional;

public class Agenda {
  private ArrayList<Contato> contatos;
  private GerenciadorArquivo gerenciador;
  private ApiCep apiClient;

  public Agenda() {
    this.gerenciador = new GerenciadorArquivo();
    this.contatos = gerenciador.carregarAgenda();
    this.apiClient = new ApiCep();
  }

  private boolean existeContato(String cpf) {
    return contatos.stream().anyMatch(c -> c.getCpf().equals(cpf));
  }

  public String criarContato(String cpf, String nome, String telefone, String cep) {
    if (existeContato(cpf)) {
      return "Contato já existe!";
    }

    if (Validador.validarCPF(cpf)) {
      Endereco enderecoObjeto = apiClient.buscaEnderecoPeloCEP(cep);
      String enderecoFinal;

      if (enderecoObjeto != null && enderecoObjeto.getLogradouro() != null) {
        enderecoFinal = enderecoObjeto.getLogradouro() + " " + enderecoObjeto.getBairro() +
                " " + enderecoObjeto.getLocalidade() + " " + enderecoObjeto.getUf();
      } else {
        enderecoFinal = "";
      }

      Contato contato = new Contato(cpf, nome, telefone, enderecoFinal);
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

  public String editarContato(String cpf, String novoNome, String novoTelefone, String novoCep) {
    Optional<Contato> contatoOptional = contatos.stream()
            .filter(c -> c.getCpf().equals(cpf))
            .findFirst();

    if (contatoOptional.isPresent()) {
      Contato contato = contatoOptional.get();
      contato.setNome(novoNome);
      contato.setTelefone(novoTelefone);

      Endereco enderecoObjeto = apiClient.buscaEnderecoPeloCEP(novoCep);
      String enderecoFinal;

      if (enderecoObjeto != null && enderecoObjeto.getLogradouro() != null) {
        enderecoFinal = enderecoObjeto.getLogradouro() + " " + enderecoObjeto.getBairro() +
                " " + enderecoObjeto.getLocalidade() + " " + enderecoObjeto.getUf();
      } else {
        enderecoFinal = "";
      }
      contato.setEndereco(enderecoFinal);
      gerenciador.salvarAgenda(contatos);
      return "Contato editado com sucesso!";
    } else {
      return "Contato não encontrado.";
    }
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
