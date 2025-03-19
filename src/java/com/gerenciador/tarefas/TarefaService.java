package com.gerenciador.tarefas;

import java.sql.SQLException;
import java.util.List;

public class TarefaService {
    private TarefaDAO tarefaDAO;

    public TarefaService(TarefaDAO tarefaDAO) {
        this.tarefaDAO = tarefaDAO; // Usa uma instância existente do DAO
    }

    // Método para adicionar uma nova tarefa
    public void adicionarTarefa(String descricao) throws SQLException {
        tarefaDAO.adicionarTarefa(descricao);
    }

    // Método para listar todas as tarefas
    public List<Tarefa> listarTarefas() throws SQLException {
        return tarefaDAO.listarTarefas();
    }

    // Método para concluir uma tarefa
    public void concluirTarefa(int id) throws SQLException {
        tarefaDAO.concluirTarefa(id);
    }

    // Método para excluir uma tarefa
    public void excluirTarefa(int id) throws SQLException {
        tarefaDAO.excluirTarefa(id);
    }
}