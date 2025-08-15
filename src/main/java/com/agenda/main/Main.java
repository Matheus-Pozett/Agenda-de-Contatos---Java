package com.agenda.main;

import com.agenda.services.Agenda;

import java.util.Scanner;

public class Main {
  static Scanner sc = new Scanner(System.in);

  public static void teclaEnter() {
    System.out.println("Tecle ENTER para continuar");
    String tecla = sc.nextLine();
  }
  public static void main(String[] args) {
    Agenda agenda = new Agenda();
    int opcao;
    String cpf, nome, telefone, cep;
    do {
      System.out.println("-------AGENDA DE CONTATOS-------");
      System.out.println("1) Novo contato");
      System.out.println("2) Consultar contato");
      System.out.println("3) Editar contato");
      System.out.println("4) Excluir contato");
      System.out.println("5) Listar contatos");
      System.out.println("6) Sair");
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
          break;
        case 2:
          System.out.print("Digite o cpf do contato que deseja consultar: ");
          cpf = sc.next();
          System.out.println(agenda.consultarContato(cpf));
          break;
        case 3:
          break;
        case 4:
          System.out.print("Digite o cpf do contato que deseja excluir: ");
          cpf = sc.next();
          agenda.excluirContato(cpf);
          break;
        case 5:
          agenda.listarContatos();
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
