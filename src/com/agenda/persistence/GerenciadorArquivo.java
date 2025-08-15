package com.agenda.persistence;

import com.agenda.model.Contato;

import java.io.*;
import java.util.ArrayList;

public class GerenciadorArquivo {
  private static final String agendaContatos = "agenda.csv";

  public void salvarAgenda(ArrayList<Contato> contatos) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(agendaContatos))) {
      writer.write("CPF;Nome;Telefone;Endereço");
      writer.newLine();

      for (Contato contato : contatos) {
        String linhaCsv = contato.formatarCsv();
        writer.write(linhaCsv);
        writer.newLine();
      }

      System.out.println("Agenda salva com sucesso no arquivo " + agendaContatos);

    } catch (IOException e) {
      System.err.println("Erro ao salvar a agenda no arquivo: " + e.getMessage());
    }
  }

  public ArrayList<Contato> carregarAgenda() {
    ArrayList<Contato> contatos = new ArrayList<>();
    File arquivo = new File(agendaContatos);

    if (!arquivo.exists()) {
      System.out.println("Nenhum arquivo de agenda encontrado. Começando com uma agenda vazia.");
      return contatos;
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(agendaContatos))) {

      reader.readLine();

      String linha;
      while ((linha = reader.readLine()) != null) {
        String[] partes = linha.split(";");

        if (partes.length == 3) {
          String cpf = partes[0];
          String nome = partes[1];
          String telefone = partes[2];

          Contato contato = new Contato(cpf, nome, telefone);

          contatos.add(contato);
        }
      }
      System.out.println(contatos.size() + " contatos carregados do arquivo.");

    } catch (IOException e) {
      System.err.println("Erro ao carregar a agenda do arquivo: " + e.getMessage());
    }
    return contatos;
  }


}
