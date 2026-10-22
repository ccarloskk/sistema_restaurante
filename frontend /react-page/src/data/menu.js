const apiBaseUrl = (import.meta.env.VITE_API_URL || "http://localhost:8080").replace(
  /\/$/,
  "",
);

export const menuItems = async () => {
  const response = await fetch(`${apiBaseUrl}/products/menu`);

  if (!response.ok) {
    throw new Error(`A API respondeu com o status ${response.status}.`);
  }

  const products = await response.json();

  if (!Array.isArray(products)) {
    throw new Error("A resposta da API não contém uma lista de produtos.");
  }

  return products.map((product) => {
    const price = Number(product.price_product);
    const description = product.description_product ?? "";

    if (
      product.idProduct == null ||
      typeof product.name_product !== "string" ||
      typeof description !== "string" ||
      typeof product.category_products !== "string" ||
      !Number.isFinite(price)
    ) {
      throw new Error("A API retornou um produto com dados inválidos.");
    }

    return {
      id: product.idProduct,
      name: product.name_product,
      description,
      price,
      category: product.category_products,
    };
  });
};

export const categories = [
  "Todos",
  "Entradas",
  "Principais",
  "Sobremesas",
  "Bebidas",
];

export const formatPrice = (value) =>
  value.toLocaleString("pt-BR", { style: "currency", currency: "BRL" });
