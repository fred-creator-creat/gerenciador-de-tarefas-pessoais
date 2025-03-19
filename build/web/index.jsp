<!DOCTYPE html>  
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gerenciador de Tarefas Pessoais</title>
    <!-- Link para o CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
    <header>
        <h1>Gerenciador de Tarefas Pessoais</h1>
    </header>

    <main>
        <section id="nova-tarefa">
            <h2>Adicionar Nova Tarefa</h2>
            <input type="text" id="tarefaInput" placeholder="Digite sua tarefa...">
            <button id="adicionarTarefa">Adicionar</button>
        </section>

        <section id="tarefas-pendentes">
            <h2>Tarefas Pendentes</h2>
            <ul id="listaPendentes"></ul>
        </section>

        <section id="tarefas-concluidas">
            <h2>Tarefas Concluídas</h2>
            <ul id="listaConcluidas"></ul>
        </section>
    </main>

    <footer>
        <p>Gerenciador de Tarefas Pessoais - Desenvolvido por [Seu Nome]</p>
    </footer>

    <!-- Link para o JS -->
    <script src="${pageContext.request.contextPath}/resources/js/script.js" defer></script>
</body>
</html>