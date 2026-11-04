export function getProductName(product) {
  return product.name_product ?? product.name ?? "";
}

export function getProductDescription(product) {
  return product.description_product ?? product.description ?? "";
}

export function getProductPrice(product) {
  return Number(product.price_product ?? product.price);
}

export function getProductCategory(product) {
  return product.category_products ?? product.category ?? "";
}

export function getProductStatus(product) {
  return product.statusProduct ?? product.status ?? true;
}
