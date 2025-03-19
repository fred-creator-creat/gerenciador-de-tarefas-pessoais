package com.gerenciador.tarefas;

import java.sql.SQLException;
import java.util.List;

/**
 * Classe responsável por controlar as operações de tarefas, delegando
 * a lógica de persistência para o TarefaDAO.
 */
public class TarefaController {
    private TarefaDAO tarefaDAO;

    /**
     * Construtor da classe TarefaController. Recebe um TarefaDAO para
     * possibilitar o controle das tarefas.
     * 
     * @param tarefaDAO - Instância do TarefaDAO que gerencia a persistência.
     */
    public TarefaController(TarefaDAO tarefaDAO) {
        this.tarefaDAO = tarefaDAO;
    }

    /**
     * Adiciona uma nova tarefa com a descrição fornecida.
     * 
     * @param descricao - A descrição da nova tarefa a ser adicionada.
     * @throws SQLException - Exceção caso ocorra um erro na persistência.
     */
    public void adicionarTarefa(String descricao) throws SQLException {
        tarefaDAO.adicionarTarefa(descricao);
    }

    /**
     * Lista todas as tarefas registradas.
     * 
     * @return Lista de todas as tarefas.
     * @throws SQLException - Exceção caso ocorra um erro ao listar as tarefas.
     */
    public List<Tarefa> listarTarefas() throws SQLException {
        return tarefaDAO.listarTarefas();
    }

    /**
     * Marca uma tarefa como concluída.
     * 
     * @param id - ID da tarefa a ser marcada como concluída.
     * @throws SQLException - Exceção caso ocorra um erro na persistência.
     */
    public void concluirTarefa(int id) throws SQLException {
        tarefaDAO.concluirTarefa(id);
    }

    /**
     * Exclui uma tarefa do sistema.
     * 
     * @param id - ID da tarefa a ser excluída.
     * @throws SQLException - Exceção caso ocorra um erro na persistência.
     */
    public void excluirTarefa(int id) throws SQLException {
        tarefaDAO.excluirTarefa(id);
    }
}