// const API_URL = "https://backend-production-e37b.up.railway.app";
const API_URL = "http://localhost:8080";
// GET: Buscar todos os produtos do menu

export async function buscarProdutos() {
  const response = await fetch(`${API_URL}/products/admin/menu`);

  if (!response.ok) {
    throw new Error("Erro ao buscar os produtos.");
  }

  return await response.json();
}

// GET: Buscar um produto pelo ID
export async function buscarProduto(idProduct) {
  const response = await fetch(`${API_URL}/products/admin/${idProduct}`);

  if (!response.ok) {
    throw new Error("Erro ao buscar o produto.");
  }

  return await response.json();
}

// POST: Criar um produto
export async function criarProduto(produto) {
  const response = await fetch(`${API_URL}/products/admin/create`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(produto),
  });

  if (!response.ok) {
    throw new Error("Erro ao criar o produto.");
  }

  if (
    response.status === 204 ||
    response.headers.get("content-length") === "0"
  ) {
    return null;
  }

  const texto = await response.text();
  return texto ? JSON.parse(texto) : null;
}

// PUT: Atualizar um produto pelo ID
export async function atualizarProduto(idProduct, produto) {
  const response = await fetch(
    `${API_URL}/products/admin/update/${idProduct}`,
    {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(produto),
    },
  );

  if (!response.ok) {
    throw new Error("Erro ao atualizar o produto.");
  }

  if (
    response.status === 204 ||
    response.headers.get("content-length") === "0"
  ) {
    return null;
  }

  const texto = await response.text();
  return texto ? JSON.parse(texto) : null;
}

// DELETE: Excluir um produto pelo ID
export async function excluirProduto(idProduct) {
  const response = await fetch(
    `${API_URL}/products/admin/delete/${idProduct}`,
    {
      method: "DELETE",
    },
  );

  if (!response.ok) {
    throw new Error("Erro ao excluir o produto.");
  }

  return true;
}
