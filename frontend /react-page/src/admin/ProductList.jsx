import { formatPrice } from "../data/menu";
import {
  getProductCategory,
  getProductDescription,
  getProductName,
  getProductPrice,
  getProductStatus,
} from "./productUtils";
import "./ProductList.css";

function ProductList({
  loading,
  products,
  totalProducts,
  hasFilters,
  saving,
  onEdit,
  onDelete,
  onClearFilters,
}) {
  if (loading) {
    return (
      <p className="admin-feedback" role="status">
        Carregando produtos...
      </p>
    );
  }

  if (!products.length) {
    return (
      <div className="empty-state admin-empty-state">
        <span aria-hidden="true">◌</span>
        <h3>Nenhum produto encontrado</h3>
        <p>
          {totalProducts
            ? "Tente outra busca ou categoria."
            : "Cadastre o primeiro produto para começar."}
        </p>
        {hasFilters && (
          <button
            className="secondary-button"
            onClick={onClearFilters}
          >
            Limpar filtros
          </button>
        )}
      </div>
    );
  }

  return (
    <div className="admin-product-list">
      {products.map((product, index) => {
        const productId = product.idProduct ?? product.id;
        const isActive = getProductStatus(product);
        return (
          <article
            className="admin-product-card"
            key={productId ?? `${getProductName(product)}-${index}`}
          >
            <div className="admin-product-info">
              <div className="admin-product-meta">
                <span className="admin-product-category">
                  {getProductCategory(product)}
                </span>
                <span
                  className={`admin-product-status ${
                    isActive ? "active" : "inactive"
                  }`}
                >
                  {isActive ? "Ativo" : "Inativo"}
                </span>
              </div>
              <h2>{getProductName(product)}</h2>
              <p>{getProductDescription(product) || "Sem descrição."}</p>
            </div>
            <strong className="admin-product-price">
              {formatPrice(getProductPrice(product))}
            </strong>
            <div className="admin-product-actions">
              <button
                className="admin-edit-button"
                onClick={() => onEdit(product)}
                disabled={saving}
              >
                Editar
              </button>
              <button
                className="admin-delete-button"
                onClick={() => onDelete(product)}
                disabled={saving}
              >
                Excluir
              </button>
            </div>
          </article>
        );
      })}
    </div>
  );
}

export default ProductList;
