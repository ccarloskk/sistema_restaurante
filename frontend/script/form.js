async function getProducts() {
  try {
    const response = await fetch('http://localhost:8080/products');
    const products = await response.json();

    const container = document.querySelector('.products-grid'); 
    container.innerHTML = ''; 

    products.forEach(product => {
      const item = document.createElement('div');
      item.classList.add('products-item');

      item.innerHTML = `
        <h3 class="name-products">${product.name_product}</h3>
        <h4 class="describe">${product.description_product|| ''}</h4>
        <p class="price">R$ ${product.price_product.toFixed(2)}</p>
        <div class="quantity-controller"></div>
      `;

      container.appendChild(item);
    });

  } catch (erro) {
    console.error("Erro ao carregar cardápio:", erro);
  }
}

window.onload = getProducts;
