// Espera o DOM estar carregado
document.addEventListener('DOMContentLoaded', function () {
    const tarefaInput = document.getElementById('tarefaInput');
    const adicionarTarefaButton = document.getElementById('adicionarTarefa');
    const listaPendentes = document.getElementById('listaPendentes');
    const listaConcluidas = document.getElementById('listaConcluidas');

    // Função para adicionar uma tarefa
    function adicionarTarefa() {
        const tarefaTexto = tarefaInput.value.trim(); // Pega o texto da tarefa

        // Verifica se o campo de tarefa não está vazio
    if (tarefaTexto === '') {
        alert('Por favor, digite uma tarefa antes de adicionar!');
        return;
    }

        // Verifica se o campo de tarefa não está vazio
        if (tarefaTexto !== '') {
            const novaTarefa = document.createElement('li'); // Cria um novo item de lista
            const textoTarefa = document.createElement('span'); // Elemento separado para o texto
            textoTarefa.textContent = tarefaTexto; // Define o texto da tarefa

            // Cria um botão para marcar como concluída
            const concluirButton = document.createElement('button');
            concluirButton.textContent = '✅ Concluir';
            concluirButton.classList.add('concluirTarefa');

            // Cria um botão para excluir a tarefa
            const excluirButton = document.createElement('button');
            excluirButton.textContent = '🗑️ Excluir';
            excluirButton.classList.add('excluirTarefa');

            // Função para mover a tarefa para a lista de concluídas
            concluirButton.addEventListener('click', function () {
                listaConcluidas.appendChild(novaTarefa); // Move a tarefa para a lista de concluídas
                novaTarefa.removeChild(concluirButton); // Remove o botão "Concluir"
                textoTarefa.style.textDecoration = 'line-through'; // Adiciona o risco sobre o texto apenas
            });

            // Função para excluir a tarefa
            excluirButton.addEventListener('click', function () {
                novaTarefa.remove(); // Remove a tarefa
            });

            novaTarefa.appendChild(textoTarefa); // Adiciona o texto dentro da tarefa
            novaTarefa.appendChild(concluirButton); // Adiciona o botão "Concluir"
            novaTarefa.appendChild(excluirButton); // Adiciona o botão "Excluir" com espaçamento
            novaTarefa.style.display = 'flex';
            novaTarefa.style.justifyContent = 'space-between';
            novaTarefa.style.alignItems = 'center';
            novaTarefa.style.gap = '10px'; // Adiciona espaçamento entre os botões

            listaPendentes.appendChild(novaTarefa); // Adiciona a nova tarefa à lista de pendentes

            // Limpa o campo de entrada após adicionar
            tarefaInput.value = '';
        }
    }

    // Adiciona evento de clique no botão de adicionar
    adicionarTarefaButton.addEventListener('click', adicionarTarefa);

    // Adiciona evento de pressionamento de tecla Enter no campo de texto
    tarefaInput.addEventListener('keypress', function (event) {
        if (event.key === 'Enter') {
            adicionarTarefa();
        }
    });
});