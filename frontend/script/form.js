const quantities = {};
const cardapioURL = "http://localhost:8080/products/menu";
const sumTotalURL = "http://localhost:8080/orders/updateTotalPublic";

async function getCardapio() {
  try {
    const response = await fetch(cardapioURL);
    const products = await response.json();

    const container = document.getElementById("products-container");
    container.innerHTML = "";

    const categorias = {};
    products.forEach(product => {
      if (!product.idProduct) return;

      const categoria = product.category_products || "Outros";

      if (!categorias[categoria]) {
        categorias[categoria] = [];
      }

      categorias[categoria].push(product);
    });

      Object.keys(categorias).forEach(categoria => {

      const categorySection = document.createElement("div");
      categorySection.classList.add("products-category");

      const title = document.createElement("h3");
      title.classList.add("category-title");
      title.textContent = categoria;

      const grid = document.createElement("div");
      grid.classList.add("products-grid");

      categorias[categoria].forEach(product => {

        const id = product.idProduct;
        quantities[id] = quantities[id] ?? 0;

        const item = document.createElement("div");
        item.classList.add("products-item");

        item.innerHTML = `
          <h3 class="name-products">${product.name_product}</h3>
          <h4 class="describe">${product.description_product || ""}</h4>
          <p class="price">R$ ${Number(product.price_product).toFixed(2)}</p>
          <div class="quantity-control">
            <button type="button" class="decrease">-</button>
            <span class="quantity">0</span>
            <button type="button" class="increase">+</button>
          </div>
        `;

        const quantitySpan = item.querySelector(".quantity");
        const increaseButton = item.querySelector(".increase");
        const decreaseButton = item.querySelector(".decrease");

        let localQty = quantities[id];

        increaseButton.addEventListener("click", () => {
          localQty++;
          quantitySpan.textContent = localQty;
          quantities[id] = localQty;
          updateSumTotal();
        });

        decreaseButton.addEventListener("click", () => {
          if (localQty > 0) {
            localQty--;
            quantitySpan.textContent = localQty;
            quantities[id] = localQty;
            updateSumTotal();
          }
        });

        grid.appendChild(item);
      });

      categorySection.appendChild(title);
      categorySection.appendChild(grid);
      container.appendChild(categorySection);
    });

  } catch (erro) {
    console.error("Erro ao carregar cardápio:", erro);
  }
}

const totalEl = document.getElementById("totalOrder");

function formatBRLNumber(value) {
  return Number(value).toLocaleString("pt-BR", {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2,
  });
}
const token = localStorage.getItem("token");

async function updateSumTotal() {
  try {
    console.debug("updateSumTotal chamado");
    console.debug("token (do localStorage):", token);

    const body = Object.entries(quantities)
      .filter(([, qty]) => qty > 0)
      .map(([id, qty]) => ({
        quantity: qty,
        product: { idProduct: Number(id) },
      }));

    console.debug("Payload (body) construído:", body);

    if (body.length === 0) {
      totalEl.textContent = formatBRLNumber(0);
      return;
    }

    const init = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        ...(token ? { Authorization: `Bearer ${token}` } : {}),
      },
      body: JSON.stringify(body),
    };

    const res = await fetch(sumTotalURL, init);

    if (!res.ok) {
      const text = await res.text();
      console.error("Servidor retornou erro:", res.status, text);
      totalEl.textContent = formatBRLNumber(0);
      return;
    }

    const sum = await res.json();
    const total =
      typeof sum === "number" ? sum : sum.total ?? sum.totalPublic ?? 0;
    totalEl.textContent = formatBRLNumber(total);
    
  } catch (err) {
    console.error("Erro em updateSumTotal:", err);
  }
}
getCardapio()

function enviarPedidoWhats() {

  const nomeCliente = document.getElementById("customerName").value;
  const telefoneCliente = document.getElementById("phone").value;
  const consumo = document.querySelector(".formaConsumo").value;

  let mensagem = "Olá, quero finalizar o seguinte pedido:%0A%0A";

  mensagem += "---- DADOS DO CLIENTE ----%0A";
  mensagem += `Nome: ${nomeCliente}%0A`;
  mensagem += `Telefone: ${telefoneCliente}%0A`;
  mensagem += `Forma de consumo: ${consumo}%0A`;
  mensagem += "--------------------------%0A%0A";

  const container = document.querySelector(".products-grid");
  const itensDOM = container.querySelectorAll(".products-item");

  let possuiItem = false;

  itensDOM.forEach(item => {
    const nome = item.querySelector(".name-products").textContent;
    const precoTexto = item.querySelector(".price").textContent;
    const quantidadeTexto = item.querySelector(".quantity").textContent;

    const qtd = Number(quantidadeTexto);

    if (qtd > 0) {
      possuiItem = true;

      const preco = precoTexto.replace("R$", "").trim();

      mensagem += `• ${nome} — Qtd: ${qtd} — Valor: R$ ${preco}%0A`;
    }
  });

  if (!possuiItem) {
    alert("Você ainda não adicionou nenhum item ao pedido.");
    return;
  }

  mensagem += "%0A";
  mensagem += `Total do pedido: R$ ${totalEl.textContent}%0A`;
  mensagem += "%0AObrigado!";

  const telefoneRestaurante = "5541996520121";

  const link = `https://wa.me/${telefoneRestaurante}?text=${mensagem}`;

  window.open(link, "_blank");
}

document
  .getElementById("sendButton")
  .addEventListener("click", enviarPedidoWhats);

