package org.example;

import java.util.HashMap;
import java.util.Map;

public class GerenciadorTarefa {

    private Map<String, Tarefa> tarefas = new HashMap<>();

    public GerenciadorTarefa() {
    }

    public void adicionarTarefa(String id, String descricao) {
        if (tarefas.get(id) == null) {
            Tarefa t = new Tarefa(id, descricao);
            tarefas.put(t.getId(), t);
            System.out.println(String.format("Tarefa [%s] adicionada com sucesso!", descricao));
        } else {
            System.out.println("Tarefa com este identificador já existe.");
        }
    }

    public void removerTarefa(String id) {
        if (tarefas.get(id) == null) {
            System.out.println("Tarefa com este identificador não foi encontrada.");
        } else {
            tarefas.remove(id);
            System.out.println(String.format("Tarefa [%s] removida com sucesso!", id));
        }
    }

    public void imprimirTarefas() {
        System.out.println("========================");
        System.out.println("Imprimindo Tarefas:");
        for(Tarefa t : tarefas.values()) {
            System.out.println(t);
        }
        System.out.println("========================");
    }

    public void finalizarTarefa(String id) {
        Tarefa tarefa = tarefas.get(id);
        if (tarefa == null) {
            System.out.println("Tarefa com este identificador não foi encontrada.");
        } else {
            tarefa.finalizarTarefa();
            System.out.println(String.format(
                    "Tarefa [%s] [%s] foi finalizada com sucesso!",
                    tarefa.getId(), tarefa.getDescricao()
            ));
        }
    }

    public void procurarTarefa(String descricao) {
        boolean tarefaEncontrada = false;
        for(Tarefa tarefa : tarefas.values()) {
            if (tarefa.getDescricao().contains(descricao)) {
                System.out.println("Tarefa encontrada!");
                System.out.println(tarefa);
                tarefaEncontrada = true;
            }
        }
        if (!tarefaEncontrada) {
            System.out.println("Tarefa não encontrada com esta descrição!");
        }
    }

}