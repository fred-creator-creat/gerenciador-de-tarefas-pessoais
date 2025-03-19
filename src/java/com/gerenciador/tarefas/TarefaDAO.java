package com.gerenciador.tarefas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {
    private Connection connection;

    public TarefaDAO() throws SQLException {
        connection = Conexao.getConnection();  // Usando a classe Conexao para obter a conexão com o banco
    }

    // Método para adicionar uma nova tarefa
    public void adicionarTarefa(String descricao) {
        String sql = "INSERT INTO tarefas (descricao, concluida) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, descricao);
            stmt.setBoolean(2, false); // Inicializa a tarefa como não concluída
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar todas as tarefas
    public List<Tarefa> listarTarefas() {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefas";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                boolean concluida = rs.getBoolean("concluida");

                Tarefa tarefa = new Tarefa(id, descricao);
                tarefa.setConcluida(concluida);
                tarefas.add(tarefa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tarefas;
    }

    // Método para concluir uma tarefa
    public void concluirTarefa(int id) {
        String sql = "UPDATE tarefas SET concluida = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setBoolean(1, true);  // Marca a tarefa como concluída
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para excluir uma tarefa
    public void excluirTarefa(int id) {
        String sql = "DELETE FROM tarefas WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}