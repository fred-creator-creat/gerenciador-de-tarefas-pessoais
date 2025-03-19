package com.gerenciador.tarefas;

public class Tarefa {
    private final int id;
    private String descricao;
    private boolean concluida;

    // Construtor para inicializar a tarefa
    public Tarefa(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
        this.concluida = false; // Inicializa a tarefa como não concluída
    }

    // Métodos getters e setters
    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}