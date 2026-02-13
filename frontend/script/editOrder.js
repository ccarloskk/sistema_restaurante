// const quantities = {};
// const cardapioURL = "http://localhost:8080/products/menu";
// const sumTotalURL = "http://localhost:8080/orders/updateTotalPublic";
// const postCreateOrder = "http://localhost:8080/order_item/CreateOrderItem";
// const createOrderURL = "http://localhost:8080/orders/createOrder";

// const token = localStorage.getItem("token");

// async function getCardapio() {
//   try {
//     const response = await fetch(cardapioURL);
//     const products = await response.json();

//     const container = document.querySelector(".products-grid");
//     container.innerHTML = "";

//     products.forEach((product) => {
//       if (!product.idProduct) return;

//       quantities[product.idProduct] = 0;

//       const item = document.createElement("div");
//       item.classList.add("products-item");

//       item.innerHTML = `
//         <h3 class="name-products">${product.name_product}</h3>
//         <h4 class="describe">${product.description_product || ""}</h4>
//         <p class="price">R$ ${Number(product.price_product).toFixed(2)}</p>

//         <div class="quantity-control">
//           <button type="button" class="decrease">-</button>
//           <span class="quantity">0</span>
//           <button type="button" class="increase">+</button>
//         </div>
//       `;

//       const quantitySpan = item.querySelector(".quantity");
//       const increaseBtn = item.querySelector(".increase");
//       const decreaseBtn = item.querySelector(".decrease");

//       let localQty = 0;

//       increaseBtn.addEventListener("click", () => {
//         localQty++;
//         quantitySpan.textContent = localQty;
//         quantities[product.idProduct] = localQty;
//         updateSumTotal();
//       });

//       decreaseBtn.addEventListener("click", () => {
//         if (localQty > 0) {
//           localQty--;
//           quantitySpan.textContent = localQty;
//           quantities[product.idProduct] = localQty;
//           updateSumTotal();
//         }
//       });

//       container.appendChild(item);
//     });
//   } catch (err) {
//     console.error("Erro ao carregar cardápio:", err);
//   }
// }

// const totalEl = document.getElementById("TotalValue");

// function formatBRLNumber(value) {
//   return Number(value).toLocaleString("pt-BR", {
//     minimumFractionDigits: 2,
//     maximumFractionDigits: 2,
//   });
// }

// async function updateSumTotal() {
//   try {
//     const body = Object.entries(quantities)
//       .filter(([, qty]) => qty > 0)
//       .map(([id, qty]) => ({
//         quantity: qty,
//         product: { idProduct: Number(id) },
//       }));

//     if (body.length === 0) {
//       totalEl.textContent = formatBRLNumber(0);
//       return;
//     }

//     const res = await fetch(sumTotalURL, {
//       method: "POST",
//       headers: {
//         "Content-Type": "application/json",
//         ...(token ? { Authorization: `Bearer ${token}` } : {}),
//       },
//       body: JSON.stringify(body),
//     });

//     if (!res.ok) throw new Error("Erro ao calcular total");

//     const sum = await res.json();
//     const total = typeof sum === "number" ? sum : sum.total ?? 0;

//     totalEl.textContent = formatBRLNumber(total);
//   } catch (err) {
//     console.error("Erro no total:", err);
//   }
// }

// function hojeYYYYMMDDLocal() {
//   const d = new Date();
//   const pad = (n) => String(n).padStart(2, "0");
//   return `${d.getFullYear()}-${pad(d.getMonth() + 1)}-${pad(d.getDate())}`;
// }

// function marcarDataDeHojeAutomatico() {
//   const hoje = hojeYYYYMMDDLocal();

//   document
//     .querySelectorAll('input[type="date"][data-default-today]')
//     .forEach((input) => {
//       if (!input.value) input.value = hoje;
//       input.addEventListener("blur", () => {
//         if (!input.value) input.value = hojeYYYYMMDDLocal();
//       });
//     });
// }

// document.addEventListener("DOMContentLoaded", () => {
//   marcarDataDeHojeAutomatico();

//   getCardapio(); 

//   const form = document.getElementById("comandaForm");
//   form.addEventListener("submit", handleSubmitComanda);
// });


// async function createOrder() {
//   const payload = {
//     name_customer: document.getElementById("nameClient").value,
//     date_order: document.getElementById("dateOrder").value,
//     status: document.getElementById("status").value,
//     total: Number(document.getElementById("total").value),
//   };

// const res = await fetch(createOrderURL, {
//   method: "POST",
//   headers: { "Content-Type": "application/json" },
//   body: JSON.stringify(payload),
//   });

//   if (!res.ok) {
//     const err = await res.text("Funcionando");
//     throw new Error(err);
//   }

//   return await res.json();
// }

document.addEventListener("DOMContentLoaded", () => {
    const params = new URLSearchParams(window.location.search);
    const idOrder = params.get("idOrder");

    if (!idOrder) {
        console.error("ID da comanda não encontrado");
        return;
    }
    const getUser = `http://localhost:8080/orders/details/${idOrder}`;

    updateUser(getUser);
});

async function updateUser(getUser) {
  try{
    const [userResponse] = await Promise.all([
      fetch(getUser)
    ])

    
    if(!userResponse.ok){
      throw new Error("Erro ao buscar usuario");
    }
    const user = await userResponse.json();

    loadUsers(user)
  }catch(error){
    console.log(error);
  }
}

function loadUsers(user) {
  const nameClient = document.getElementById("nameClient");
  const dateOrder = document.getElementById("dateOrder");
  const status = document.getElementById("status");

  const order = user[0];

  nameClient.value = order.name_customer;
  dateOrder.value = order.date_order.split("T")[0];
  status.value = order.status;
}








