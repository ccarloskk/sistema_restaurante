const quantities = {};
const cardapioURL = "http://localhost:8080/products/menu";
const sumTotalURL = "http://localhost:8080/orders/updateTotalPublic";

async function getCardapio() {
  try {
    const response = await fetch(cardapioURL);
    const products = await response.json();
    const container = document.querySelector(".products-grid");
    container.innerHTML = "";

    products.forEach((product) => {
      if (product.idProduct == null) {
        return;
      }
      
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
        localQty += 1;
        quantitySpan.textContent = localQty;
        quantities[product.idProduct] = localQty;
        updateSumTotal();
      });

      decreaseButton.addEventListener("click", () => {
        if (localQty > 0) {
          localQty -= 1;
          quantitySpan.textContent = localQty;
          quantities[product.idProduct] = localQty;
          updateSumTotal();
        }
      });

      container.appendChild(item);
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
// setInterval(getCardapio, 3000)