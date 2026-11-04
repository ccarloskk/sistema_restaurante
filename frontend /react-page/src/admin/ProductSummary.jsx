import "./ProductSummary.css";

function ProductSummary({ productCount, categoryCount }) {
  return (
    <div className="admin-summary" aria-label="Resumo dos produtos">
      <div className="admin-summary-card">
        <span className="admin-summary-icon" aria-hidden="true">
          ▤
        </span>
        <div>
          <span>Produtos cadastrados</span>
          <strong>{productCount}</strong>
        </div>
      </div>
      <div className="admin-summary-card">
        <span className="admin-summary-icon" aria-hidden="true">
          ◈
        </span>
        <div>
          <span>Categorias no cardápio</span>
          <strong>{categoryCount}</strong>
        </div>
      </div>
    </div>
  );
}

export default ProductSummary;
