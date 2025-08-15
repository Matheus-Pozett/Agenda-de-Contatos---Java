package com.agenda.model;

public class Contato {
  private final String cpf;
  private String nome;
  private String telefone;

  public Contato(String cpf, String nome, String telefone) {
    this.cpf = cpf;
    this.nome = nome;
    this.telefone = telefone;
  }

  public String getCpf() {
    return cpf;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  @Override
  public String toString() {
    return "Contato{" +
            "cpf='" + cpf + '\'' +
            ", nome='" + nome + '\'' +
            ", telefone='" + telefone + '\'' +
            '}';
  }

  public String formatarCsv() {
    return this.cpf + ";" + this.nome + ";" + this.telefone;
  }
}
