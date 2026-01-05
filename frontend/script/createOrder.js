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

      quantities[product.idProduct] = quantities[product.idProduct] ?? 0;

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

      let localQty = 0;

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

function hojeYYYYMMDDLocal() {
  const d = new Date();
  const pad = (n) => String(n).padStart(2, "0");
  return `${d.getFullYear()}-${pad(d.getMonth() + 1)}-${pad(d.getDate())}`;
}

function marcarDataDeHojeAutomatico() {
  const hoje = hojeYYYYMMDDLocal();

  document.querySelectorAll('input[type="date"][data-default-today]').forEach((input) => {
    if (!input.value) input.value = hoje;

    input.addEventListener("blur", () => {
      if (!input.value) input.value = hojeYYYYMMDDLocal();
    });
  });
}

document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("comandaForm");
  form.addEventListener("submit", createOrder);
});

async function createOrder(event) {
  event.preventDefault();

  const order_URL = "http://localhost:8080/orders/createOrder";

  const payload = {
    name_customer: document.getElementById("nameClient").value,
    date_order: document.getElementById("dateOrder").value,
    status: document.getElementById("status").value,
    total: Number(document.getElementById("total").value),
  };

  const res = await fetch(order_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload),
  });

  if (!res.ok) {
    const errText = await res.text();
    throw new Error(`HTTP ${res.status} - ${errText}`);
  }

  const data = await res.json();
  console.log("Pedido criado:", data);
}

getCardapio()
hojeYYYYMMDDLocal()