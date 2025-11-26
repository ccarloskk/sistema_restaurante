// const produtos = [
//     { id: 1, nome: "Hambúrguer", preco: 22.50 },
//     { id: 2, nome: "Pizza Margherita", preco: 35.00 },
//     { id: 3, nome: "Refrigerante", preco: 6.00 },
//     { id: 4, nome: "Batata Frita", preco: 12.00 }
// ];

// const params = new URLSearchParams(window.location.search);
// const idComanda = params.get("id");

// const listaProdutos = document.getElementById("listaProdutos");
// const totalPedidoEl = document.getElementById("totalPedido");
// const itensSelecionadosEl = document.getElementById("itensSelecionados");
// const form = document.getElementById("pedidoForm");
// const titulo = document.getElementById("tituloPagina");

// let comandaAtual = { id: null, nome: "", telefone: "", itens: [], total: 0, status: "Ativa" };

// document.addEventListener("DOMContentLoaded", () => {
//     carregarProdutos();

//     if (idComanda) {
//         titulo.textContent = `Editar Comanda #${idComanda}`;
//         carregarComandaExistente(idComanda);
//     }
// });

// function carregarProdutos() {
//     listaProdutos.innerHTML = "";
//     produtos.forEach(p => {
//         const item = document.createElement("div");
//         item.classList.add("produto-item");
//         item.innerHTML = `
//             <h3>${p.nome}</h3>
//             <p>R$ ${p.preco.toFixed(2)}</p>
//             <input type="number" min="0" value="0" class="quantidade" data-id="${p.id}" data-preco="${p.preco}">
//         `;
//         listaProdutos.appendChild(item);
//     });

//     document.querySelectorAll(".quantidade").forEach(input => {
//         input.addEventListener("input", atualizarResumo);
//     });
// }

// function atualizarResumo() {
//     let total = 0;
//     let itens = [];

//     document.querySelectorAll(".quantidade").forEach(input => {
//         const quantidade = parseInt(input.value);
//         const preco = parseFloat(input.dataset.preco);
//         const nome = input.parentElement.querySelector("h3").textContent;

//         if (quantidade > 0) {
//             const subtotal = quantidade * preco;
//             total += subtotal;
//             itens.push({ nome, quantidade, subtotal });
//         }
//     });

//     comandaAtual.itens = itens;
//     comandaAtual.total = total;

//     itensSelecionadosEl.innerHTML = itens.length
//         ? itens.map(i => `<p>${i.nome} x${i.quantidade} - R$ ${i.subtotal.toFixed(2)}</p>`).join("")
//         : "<p>Nenhum item selecionado</p>";

//     totalPedidoEl.textContent = total.toFixed(2);
// }

// function carregarComandaExistente(id) {
//     const comandas = JSON.parse(localStorage.getItem("comandas")) || [];
//     const comanda = comandas.find(c => c.id == id);
//     if (!comanda) return;

//     document.getElementById("nome").value = comanda.nome;
//     document.getElementById("telefone").value = comanda.telefone;
//     comanda.itens.forEach(item => {
//         const input = document.querySelector(`[data-id='${produtos.find(p => p.nome === item.nome).id}']`);
//         if (input) input.value = item.quantidade;
//     });
//     atualizarResumo();
//     comandaAtual = comanda;
// }

// form.addEventListener("submit", (e) => {
//     e.preventDefault();

//     const nome = document.getElementById("nome").value;
//     const telefone = document.getElementById("telefone").value;

//     let comandas = JSON.parse(localStorage.getItem("comandas")) || [];

//     if (idComanda) {
//         // Atualizando comanda existente
//         comandas = comandas.map(c => {
//             if (c.id == idComanda) {
//                 return { ...c, nome, telefone, itens: comandaAtual.itens, total: comandaAtual.total };
//             }
//             return c;
//         });
//     } else {
//         // Criando nova comanda
//         const novoId = comandas.length ? comandas[comandas.length - 1].id + 1 : 1;
//         comandas.push({ id: novoId, nome, telefone, ...comandaAtual });
//     }

//     localStorage.setItem("comandas", JSON.stringify(comandas));
//     alert("Comanda salva com sucesso!");
//     window.location.href = "order.html";
// });
