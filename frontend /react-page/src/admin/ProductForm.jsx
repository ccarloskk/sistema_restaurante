import "./ProductForm.css";

function ProductForm({
  form,
  isEditing,
  saving,
  categories,
  onChange,
  onSubmit,
  onClose,
}) {
  return (
    <div
      className="admin-modal-backdrop"
      role="presentation"
      onMouseDown={(event) => {
        if (event.target === event.currentTarget && !saving) onClose();
      }}
    >
      <section
        className="admin-modal"
        role="dialog"
        aria-modal="true"
        aria-labelledby="admin-form-title"
      >
        <div className="admin-modal-heading">
          <div>
            <p className="eyebrow">
              {isEditing ? "Atualização do cardápio" : "Novo cadastro"}
            </p>
            <h2 id="admin-form-title">
              {isEditing ? "Editar produto" : "Novo produto"}
            </h2>
          </div>
          <button
            type="button"
            className="admin-modal-close"
            aria-label="Fechar formulário"
            onClick={onClose}
            disabled={saving}
          >
            ×
          </button>
        </div>
        <form className="admin-form" onSubmit={onSubmit}>
          <label>
            Nome do produto
            <input
              required
              maxLength={120}
              value={form.name_product}
              onChange={(event) => onChange("name_product", event.target.value)}
              autoFocus
            />
          </label>
          <label>
            Descrição
            <textarea
              maxLength={500}
              rows={4}
              value={form.description_product}
              onChange={(event) =>
                onChange("description_product", event.target.value)
              }
            />
          </label>
          <div className="admin-form-row">
            <label>
              Preço (R$)
              <input
                required
                type="number"
                min="0"
                step="0.01"
                value={form.price_product}
                onChange={(event) =>
                  onChange("price_product", event.target.value)
                }
              />
            </label>
            <label>
              Categoria
              <select
                required
                value={form.category_products}
                onChange={(event) =>
                  onChange("category_products", event.target.value)
                }
              >
                {categories.map((item) => (
                  <option key={item} value={item}>
                    {item}
                  </option>
                ))}
              </select>
            </label>
          </div>
          <label className="admin-status-field">
            <input
              type="checkbox"
              checked={form.status}
              onChange={(event) => onChange("status", event.target.checked)}
            />
            Produto ativo no cardápio
          </label>
          <div className="admin-form-actions">
            <button
              type="button"
              className="secondary-button"
              onClick={onClose}
              disabled={saving}
            >
              Cancelar
            </button>
            <button
              type="submit"
              className="primary-button"
              disabled={saving}
            >
              {saving ? "Salvando..." : "Salvar produto"}
            </button>
          </div>
        </form>
      </section>
    </div>
  );
}

export default ProductForm;
