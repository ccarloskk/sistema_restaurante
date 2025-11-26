// document.addEventListener("DOMContentLoaded", carregarComandas);

// function carregarComandas() {
//     const listaComandas = document.getElementById('listaComandas');
//     const comandas = JSON.parse(localStorage.getItem('comandas')) || [];

//     listaComandas.innerHTML = "";

//     if (comandas.length === 0) {
//         listaComandas.innerHTML = "<p>Nenhuma comanda criada ainda.</p>";
//         return;
//     }

//     comandas.forEach((c, i) => {
//         const div = document.createElement('div');
//         div.classList.add('comanda-card');
//         div.innerHTML = `
//             <h3>Comanda #${c.id}</h3>
//             <p><strong>Cliente:</strong> ${c.nome}</p>
//             <p><strong>Total:</strong> R$ ${c.total.toFixed(2)}</p>
//             <p><strong>Status:</strong> ${c.status}</p>
//             <div class="acoes">
//                 <button onclick="editarComanda(${c.id})">Editar</button>
//                 <button onclick="finalizarComanda(${c.id})">Finalizar</button>
//             </div>
//         `;
//         listaComandas.appendChild(div);
//     });
// }

// function editarComanda(id) {
//     window.location.href = `createorder.html?id=${id}`;
// }

// function finalizarComanda(id) {
//     let comandas = JSON.parse(localStorage.getItem('comandas')) || [];
//     comandas = comandas.map(c => {
//         if (c.id === id) c.status = "Finalizada";
//         return c;
//     });
//     localStorage.setItem('comandas', JSON.stringify(comandas));
//     carregarComandas();
// }
