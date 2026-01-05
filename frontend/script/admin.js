const cardapioURL = "http://localhost:8080/products/admin/menu";

async function getCardapioAdmin() {
  try {
    const response = await fetch(cardapioURL);
    const products = await response.json();

    const container = document.querySelector("#productRow");
    container.innerHTML = "";

    products.forEach((product) => {
      if (product.idProduct == null) {
        return;
      }

      let statusText;
      let statusClass;

      if (product.status == 0) {
        statusText = 'Desativado';
        statusClass = 'unavailable';;
      } else if (product.status == 1) {
        statusText = 'Ativado';
        statusClass = 'available';
      } else {
        statusText = 'Desconhecido';
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
        <td id= "status"><span class="status-pill ${statusClass}">${statusText}</span></td>
        <td>
          <button type="button" class="btn icon" aria-label="Editar" onclick="window.location.href='/frontend/admin/editProd.html?idProduct=${product.idProduct}'">✏️</button>        
        </td>
      `;

      container.appendChild(item);
    });

  } catch (erro) {
    console.error("Erro ao carregar cardápio:", erro);
  }
}

document.addEventListener('DOMContentLoaded', () => {
  const form = document.querySelector('.form-grid');

  if (!form) {
    console.error('Formulário .form-grid NÃO encontrado!');
    return;
  }

    form.addEventListener('submit', async (event) => {
    event.preventDefault();
    console.log('Submit do formulário disparou');

    const nome = document.getElementById('nameProduct').value;
    const categoria = document.getElementById('category').value;
    const preco = document.getElementById('price').value;
    const disponivel = document.getElementById('available').checked;
    const descricao = document.querySelector('textarea').value;

    const products = {
      name_product: nome,
      description_product: descricao,
      category_products: categoria,
      price_product: Number(preco),
      status: disponivel
    };

    console.log('Enviando produto:', products);

    try {
      const resposta = await fetch('http://localhost:8080/products/create', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(products),
      });

      console.log('Status da resposta:', resposta.status);

      if (!resposta.ok) {
        const erroTexto = await resposta.text();
        console.error('Erro do servidor:', erroTexto);
        throw new Error('Erro ao salvar produto');
      }

      const data = await resposta.json();
      console.log('Resposta do backend:', data);

      alert('Produto salvo com sucesso!');
      window.location.href = '/frontend/admin/admin.html';
    } catch (erro) {
      console.error('Erro no fetch:', erro);
      alert('Erro ao salvar produto. Tente novamente.');
    }
  });
});

getCardapioAdmin();