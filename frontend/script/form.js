<script>
        document.addEventListener('DOMContentLoaded', function() {
            document.querySelectorAll('.aumentar').forEach(btn => {
                btn.addEventListener('click', function() {
                    const id = this.getAttribute('data-id');
                    const input = document.querySelector(`.quantidade[data-id="${id}"]`);
                    input.value = parseInt(input.value) + 1;
                    atualizarPedido();
                });
            });

            document.querySelectorAll('.diminuir').forEach(btn => {
                btn.addEventListener('click', function() {
                    const id = this.getAttribute('data-id');
                    const input = document.querySelector(`.quantidade[data-id="${id}"]`);
                    if (parseInt(input.value) > 0) {
                        input.value = parseInt(input.value) - 1;
                        atualizarPedido();
                    }
                });
            });

            document.querySelectorAll('.quantidade').forEach(input => {
                input.addEventListener('change', atualizarPedido);
            });

            document.getElementById('pedidoForm').addEventListener('submit', function(e) {
                e.preventDefault();
                finalizarPedido();
            });

            document.querySelector('.mobile-toggle').addEventListener('click', function() {
                document.querySelector('.main-nav').classList.toggle('active');
            });
        });

        function atualizarPedido() {
            let total = 0;
            let itensHTML = '';
            
            document.querySelectorAll('.quantidade').forEach(input => {
                const id = input.getAttribute('data-id');
                const quantidade = parseInt(input.value);
                const preco = parseFloat(input.getAttribute('data-preco'));
                
                if (quantidade > 0) {
                    const nome = document.querySelector(`.produto-item h3[data-id="${id}"]`)?.textContent || 'Produto';
                    const subtotal = quantidade * preco;
                    total += subtotal;
                    
                    itensHTML += `
                        <div class="item-pedido">
                            <span>${quantidade}x ${nome}</span>
                            <span>R$ ${subtotal.toFixed(2).replace('.', ',')}</span>
                        </div>
                    `;
                }
            });
            
            document.getElementById('itensSelecionados').innerHTML = itensHTML || '<p>Nenhum item selecionado</p>';
            document.getElementById('totalPedido').textContent = total.toFixed(2).replace('.', ',');
        }

        function finalizarPedido() {
            const nome = document.getElementById('nome').value;
            const telefone = document.getElementById('telefone').value;
            const itens = [];
            let total = 0;
            
            document.querySelectorAll('.quantidade').forEach(input => {
                const id = input.getAttribute('data-id');
                const quantidade = parseInt(input.value);
                
                if (quantidade > 0) {
                    const preco = parseFloat(input.getAttribute('data-preco'));
                    const nomeProduto = document.querySelector(`.produto-item h3[data-id="${id}"]`)?.textContent || 'Produto';
                    const subtotal = quantidade * preco;
                    
                    itens.push({
                        id: id,
                        nome: nomeProduto,
                        quantidade: quantidade,
                        preco: preco,
                        subtotal: subtotal
                    });
                    
                    total += subtotal;
                }
            });
            
            if (itens.length === 0) {
                alert('Por favor, selecione pelo menos um item.');
                return;
            }
            
            if (!nome || !telefone) {
                alert('Por favor, preencha seu nome e telefone.');
                return;
            }
            
            fetch('finalizar_pedido.php', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    nome: nome,
                    telefone: telefone,
                    itens: itens,
                    total: total
                })
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert('Pedido realizado com sucesso! Obrigado.');
                    window.location.reload();
                } else {
                    alert('Erro ao finalizar pedido: ' + (data.message || 'Tente novamente.'));
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Erro ao finalizar pedido. Tente novamente.');
            });
        }
    </script>
</body>
</html>