package com.agenda.main;

import com.agenda.services.Agenda;

import java.io.IOException;
import java.util.Scanner;

public class Main {

  public static void limpaConsole() throws IOException, InterruptedException {
    String os = System.getProperty("os.name");

    if (os.contains("Windows")) {
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    } else {
      new ProcessBuilder("clear").inheritIO().start().waitFor();
    }
  }

  static Scanner sc = new Scanner(System.in);

  public static void teclaEnter() {
    System.out.println("Tecle ENTER para continuar");
    String tecla = sc.nextLine();
  }
  public static void main(String[] args) throws IOException, InterruptedException {
    Agenda agenda = new Agenda();
    int opcao;
    String cpf, nome, telefone, cep;
    do {
      limpaConsole();
      System.out.println("-------AGENDA DE CONTATOS-------");
      System.out.println("1) Novo contato");
      System.out.println("2) Consultar contato");
      System.out.println("3) Editar contato");
      System.out.println("4) Excluir contato");
      System.out.println("5) Listar contatos");
      System.out.println("6) Sair");
      System.out.print("Escolha a opção: ");
      opcao = sc.nextInt();
      sc.nextLine();

      switch (opcao) {
        case 1:
          System.out.print("Digite o cpf: ");
          cpf = sc.next();
          sc.nextLine();
          System.out.print("Digite o nome: ");
          nome = sc.nextLine();

          System.out.print("Digite o telefone: ");
          telefone = sc.next();
          sc.nextLine();

          System.out.print("Digite o CEP (apenas números): ");
          cep = sc.nextLine();

          System.out.println(agenda.criarContato(cpf, nome, telefone, cep));
          teclaEnter();
          limpaConsole();
          break;
        case 2:
          System.out.print("Digite o cpf do contato que deseja consultar: ");
          cpf = sc.next();
          System.out.println(agenda.consultarContato(cpf));
          teclaEnter();
          break;
        case 3:
          System.out.print("Digite o cpf do contato que deseja editar: ");
          cpf = sc.next();
          sc.nextLine();
          System.out.print("Digite o novo nome: ");
          nome = sc.nextLine();
          System.out.print("Digite o novo telefone: ");
          telefone = sc.next();
          sc.nextLine();
          System.out.print("Digite o novo CEP: ");
          cep = sc.nextLine();
          System.out.println(agenda.editarContato(cpf, nome, telefone, cep));
          teclaEnter();
          break;
        case 4:
          System.out.print("Digite o cpf do contato que deseja excluir: ");
          cpf = sc.next();
          agenda.excluirContato(cpf);
          teclaEnter();
          break;
        case 5:
          agenda.listarContatos();
          teclaEnter();
          break;
        case 6:
          break;
        default:
          System.out.println("Opção inválida");
          teclaEnter();
          break;
      }


    } while (opcao != 6);
  }
}
