document.addEventListener('DOMContentLoaded', function () {
    const tarefaInput = document.getElementById('tarefaInput');
    const adicionarTarefaButton = document.getElementById('adicionarTarefa');
    const listaPendentes = document.getElementById('listaPendentes');
    const listaConcluidas = document.getElementById('listaConcluidas');

    // Função para adicionar uma tarefa via AJAX
    function adicionarTarefa() {
        const tarefaTexto = tarefaInput.value.trim(); // Pega o texto da tarefa

        if (tarefaTexto === '') {
            alert('Por favor, digite uma tarefa antes de adicionar!');
            return;
        }

        // Fazendo uma requisição AJAX para adicionar a tarefa
        const xhr = new XMLHttpRequest();
        xhr.open('POST', '/adicionarTarefa', true); // Envia para o servlet que vai adicionar a tarefa
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                const novaTarefa = document.createElement('li');
                const textoTarefa = document.createElement('span');
                textoTarefa.textContent = tarefaTexto;

                // Criando os botões de ação
                const concluirButton = document.createElement('button');
                concluirButton.textContent = '✅ Concluir';
                concluirButton.classList.add('concluirTarefa');

                const excluirButton = document.createElement('button');
                excluirButton.textContent = '🗑️ Excluir';
                excluirButton.classList.add('excluirTarefa');

                // Adicionando a tarefa à lista de pendentes
                concluirButton.addEventListener('click', function () {
                    listaConcluidas.appendChild(novaTarefa);
                    novaTarefa.removeChild(concluirButton);
                    textoTarefa.style.textDecoration = 'line-through';
                });

                excluirButton.addEventListener('click', function () {
                    novaTarefa.remove();
                });

                novaTarefa.appendChild(textoTarefa);
                novaTarefa.appendChild(concluirButton);
                novaTarefa.appendChild(excluirButton);
                listaPendentes.appendChild(novaTarefa);

                // Limpa o campo de entrada
                tarefaInput.value = '';
            }
        };
        // Envia a tarefa para o servlet
        xhr.send('tarefaTexto=' + encodeURIComponent(tarefaTexto));
    }

    adicionarTarefaButton.addEventListener('click', adicionarTarefa);
    tarefaInput.addEventListener('keypress', function (event) {
        if (event.key === 'Enter') {
            adicionarTarefa();
        }
    });
});