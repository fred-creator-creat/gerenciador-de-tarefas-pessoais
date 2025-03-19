package com.gerenciador.tarefas;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.List;
import org.json.JSONObject; // Adicionar import para JSON

public class TarefaServlet extends HttpServlet {
    private TarefaController tarefaController;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            TarefaDAO tarefaDAO = new TarefaDAO(); // Criando uma instância do TarefaDAO
            tarefaController = new TarefaController(tarefaDAO); // Passando a instância para o TarefaController
        } catch (SQLException ex) {
            throw new ServletException("Erro ao conectar ao banco de dados", ex);
        }
    }

    // Método para exibir a lista de tarefas
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("listar".equals(action)) {
            try {
                List<Tarefa> tarefas = tarefaController.listarTarefas();
                request.setAttribute("tarefas", tarefas); // Passando as tarefas para a view
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
            } catch (SQLException ex) {
                throw new ServletException("Erro ao listar tarefas", ex);
            }
        }
    }

    // Método para tratar adição, conclusão e exclusão de tarefas
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if (action != null) {
                switch (action) {
                    case "adicionar" -> {
                        String descricao = request.getParameter("descricao");
                        if (descricao != null && !descricao.trim().isEmpty()) {
                            tarefaController.adicionarTarefa(descricao);
                            enviarRespostaJson(response, "success", "Tarefa adicionada com sucesso!");
                        } else {
                            enviarRespostaJson(response, "error", "Descrição da tarefa não pode ser vazia!");
                        }
                    }
                    case "concluir" -> {
                        String idParam = request.getParameter("id");
                        if (idParam != null && idParam.matches("\\d+")) {
                            int id = Integer.parseInt(idParam);
                            tarefaController.concluirTarefa(id);
                            enviarRespostaJson(response, "success", "Tarefa concluída com sucesso!");
                        } else {
                            enviarRespostaJson(response, "error", "ID inválido para concluir tarefa.");
                        }
                    }
                    case "excluir" -> {
                        String idParam = request.getParameter("id");
                        if (idParam != null && idParam.matches("\\d+")) {
                            int id = Integer.parseInt(idParam);
                            tarefaController.excluirTarefa(id);
                            enviarRespostaJson(response, "success", "Tarefa excluída com sucesso!");
                        } else {
                            enviarRespostaJson(response, "error", "ID inválido para excluir tarefa.");
                        }
                    }
                    default -> {
                        throw new ServletException("Ação inválida: " + action);
                    }
                }
            }
        } catch (SQLException ex) {
            throw new ServletException("Erro ao processar requisição", ex);
        }
    }

    // Método para enviar uma resposta JSON para o cliente
    private void enviarRespostaJson(HttpServletResponse response, String status, String mensagem) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", status);
        jsonResponse.put("mensagem", mensagem);

        response.getWriter().write(jsonResponse.toString());
    }
}