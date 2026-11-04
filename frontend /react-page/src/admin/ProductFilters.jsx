import "./ProductFilters.css";

function ProductFilters({
  categories,
  selectedCategory,
  onCategoryChange,
  search,
  onSearchChange,
}) {
  return (
    <div className="admin-toolbar">
      <div className="category-tabs" aria-label="Filtrar por categoria">
        {["Todos", ...categories].map((item) => (
          <button
            key={item}
            className={selectedCategory === item ? "selected" : ""}
            aria-pressed={selectedCategory === item}
            onClick={() => onCategoryChange(item)}
          >
            {item}
          </button>
        ))}
      </div>
      <label className="search-box">
        <span aria-hidden="true">⌕</span>
        <input
          type="search"
          aria-label="Buscar produtos"
          value={search}
          onChange={(event) => onSearchChange(event.target.value)}
          placeholder="Buscar produto"
        />
      </label>
    </div>
  );
}

export default ProductFilters;
