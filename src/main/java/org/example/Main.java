package org.example;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        GerenciadorTarefa gerenciadorTarefa = new GerenciadorTarefa();
        System.out.println("Bem vindo ao TaskFlow!");

        // 1. comecar um loop
        boolean finalizarTaskFlow = false;
        do {

            // 1.1. printar o menu
            printMenu();

            // 1.2. ler a selecao do usuario
            int opcao = sc.nextInt();
            sc.nextLine();

            // 1.3. rotear para a funcionalidade especifica
            switch (opcao) {
                case 1:
                    adicionarTarefa(sc, gerenciadorTarefa);
                    break;
                case 2:
                    removerTarefaPorId(sc, gerenciadorTarefa);
                    break;
                case 3:
                    gerenciadorTarefa.imprimirTarefas();
                    break;
                case 4:
                    finalizarTarefa(sc, gerenciadorTarefa);
                    break;
                case 5:
                    procurarTarefa(sc, gerenciadorTarefa);
                    break;
                case 6:
                    finalizarTaskFlow = true;
                    break;
            }
        } while (!finalizarTaskFlow);
    }

    private static void procurarTarefa(Scanner sc, GerenciadorTarefa gerenciadorTarefa) {
        System.out.println("Digite a descrição:");
        String descricao = sc.nextLine();
        gerenciadorTarefa.procurarTarefa(descricao);
    }

    private static void finalizarTarefa(Scanner sc, GerenciadorTarefa gerenciadorTarefa) {
        System.out.println("Digite o ID:");
        String id = sc.nextLine();
        gerenciadorTarefa.finalizarTarefa(id);
    }

    private static void removerTarefaPorId(Scanner sc, GerenciadorTarefa gerenciadorTarefa) {
        System.out.println("Digite o ID:");
        String id = sc.nextLine();
        gerenciadorTarefa.removerTarefa(id);
    }

    private static void adicionarTarefa(Scanner sc, GerenciadorTarefa gerenciadorTarefa) {
        System.out.println("Digite o ID:");
        String id = sc.nextLine();
        System.out.println("Digite a descrição:");
        String descricao = sc.nextLine();
        gerenciadorTarefa.adicionarTarefa(id, descricao);
    }

    private static void printMenu() {
        System.out.println("#################################");
        System.out.println("Escolha uma opção: ");
        System.out.println("1. Adicionar Tarefa");
        System.out.println("2. Remover Tarefa por ID");
        System.out.println("3. Listar Tarefas");
        System.out.println("4. Marcar tarefa como concluida");
        System.out.println("5. Procurar tarefa");
        System.out.println("6. Sair do TaskFlow");
        System.out.println("#################################");
    }
}