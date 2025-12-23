const ProductURL = 'http://localhost:8080/products/';

function collectId() {
  const params = new URLSearchParams(window.location.search);
  return params.get('idProduct'); 
}

async function getCardapio(idProduct) {
  try {
    const response = await fetch(`${ProductURL}${idProduct}`);

    if (!response.ok) {
      const erroTexto = await response.text();
      console.error('Erro ao carregar produto:', erroTexto);
      alert('Não foi possível carregar o produto. Verifique se o ID é válido.');
      return;
    }

    const product = await response.json();
    console.log('Produto carregado:', product);

    document.getElementById('nameProduct').value = product.name_product ?? '';
    document.getElementById('category').value = product.category_products ?? '';
    document.getElementById('price').value = product.price_product ?? '';
    document.getElementById('available').checked = Boolean(product.status);
    document.getElementById('description').value = product.description_product ?? '';

  } catch (erro) {
    console.error('Erro no fetch (GET):', erro);
    alert('Erro inesperado ao carregar o produto.');
  }
}

async function updateProduct(event) {
  event.preventDefault();

  const idProduct = collectId();
  if (!idProduct) {
    alert('Produto não encontrado');
    return;
  }

  const name = document.getElementById('nameProduct').value.trim();
  const category = document.getElementById('category').value.trim();
  const price = document.getElementById('price').value;
  const disponivel = document.getElementById('available').checked;
  const descricao = document.getElementById('description').value.trim();

  const updatedProduct = {
    nameProduct: name,
    descriptionProduct: descricao,
    categoryProducts: category,
    priceProduct: Number(price),
    statusProduct: disponivel
  };

  try {
    const resposta = await fetch(`${ProductURL}${idProduct}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(updatedProduct),
    });

    if (!resposta.ok) {
      const erroTexto = await resposta.text();
      console.error('Erro do servidor:', erroTexto);
      alert('Erro ao atualizar produto. Verifique os dados e tente novamente.');
      return;
    }

    const retorno = await resposta.json();
    console.log('Produto atualizado com sucesso:', retorno);

    alert('Produto atualizado!');
    window.location.href = '/frontend/admin/admin.html';
  } catch (erro) {
    console.error('Erro no fetch (PUT):', erro);
    alert('Erro ao atualizar produto. Tente novamente mais tarde.');
  }
}

function initializePage() {
  const form = document.querySelector('.form-grid');

  if (!form) {
    console.error('Formulário .form-grid NÃO encontrado!');
    alert('Formulário não encontrado na página.');
    return;
  }

  const idProduct = collectId();
  if (!idProduct) {
    alert('Nenhum ID de produto informado. Redirecionando...');
    window.location.href = '/frontend/admin/admin.html';
    return;
  }

  getCardapio(idProduct);

  form.addEventListener('submit', updateProduct);
}

document.addEventListener('DOMContentLoaded', initializePage);