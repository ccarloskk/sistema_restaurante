const cardapioURL = "http://localhost:8080/products/menu";

async function getCardapioAdmin() {
  try {
    const response = await fetch(cardapioURL);
    const products = await response.json();

    const container = document.querySelector("#productRow");
    container.innerHTML = "";

    products.forEach((product) => {
      if (product.id_product == null) {
        return;
      }

      const item = document.createElement("tr");
      item.id = "prpductDescribe";
      item.innerHTML = `
        <td>
          <div class="product-cell">
            <div>
              <strong id="product">${product.name_product}</strong>
              <small id="description">${product.description_product}</small>
            </div>
          </div>
        </td>
        <td id="dishCategory">${product.category_products}</td>
        <td id="price">R$ ${product.price_product}</td>
        <td id= "status">${product.status}<span class="status-pill available"></span></td>
        <td>
          <button type="button" class="btn icon" aria-label="Editar">✏️</button>
          <button type="button" class="btn icon" aria-label="Destacar">⭐</button>
          <button type="button" class="btn icon danger" aria-label="Excluir">🗑️</button>
        </td>
      `;
      
      container.appendChild(item);
    });

  } catch (erro) {
    console.error("Erro ao carregar cardápio:", erro);
  }
}

getCardapioAdmin();