package org.example;

public class Tarefa {

    private String id;
    private String descricao;
    private boolean finalizada;

    public Tarefa(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
        this.finalizada = false;
    }

    public String getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public void finalizarTarefa() {
        this.finalizada = true;
    }

    @Override
    public String toString() {
        String estaFinalizada = this.finalizada ? "FINALIZADA" : "EM ANDAMENTO";
        return String.format("Tarefa [id=%s] [descrição=%s] [%s]", this.id, this.descricao, estaFinalizada);
    }
}