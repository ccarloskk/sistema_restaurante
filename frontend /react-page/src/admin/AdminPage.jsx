import { useEffect, useMemo, useState } from "react";
import {
  atualizarProduto,
  buscarProdutos,
  criarProduto,
  excluirProduto,
} from "../admin/products";
import { categories, formatPrice } from "../data/menu";
import "./AdminPage.css";

const productCategories = categories.filter((category) => category !== "Todos");
const emptyForm = {
  name_product: "",
  description_product: "",
  price_product: "",
  category_products: productCategories[0] ?? "",
  status: true,
};

function getProductName(product) {
  return product.name_product ?? product.name ?? "";
}

function getProductDescription(product) {
  return product.description_product ?? product.description ?? "";
}

function getProductPrice(product) {
  return Number(product.price_product ?? product.price);
}

function getProductCategory(product) {
  return product.category_products ?? product.category ?? "";
}

async function fetchProducts() {
  const result = await buscarProdutos();
  if (!Array.isArray(result)) {
    throw new Error("A API não retornou uma lista de produtos.");
  }
  return result;
}

function AdminPage() {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [saving, setSaving] = useState(false);
  const [error, setError] = useState("");
  const [search, setSearch] = useState("");
  const [category, setCategory] = useState("Todos");
  const [editingProduct, setEditingProduct] = useState(null);
  const [isFormOpen, setIsFormOpen] = useState(false);
  const [form, setForm] = useState(emptyForm);

  useEffect(() => {
    let isMounted = true;

    async function load() {
      try {
        const result = await fetchProducts();
        if (isMounted) {
          setProducts(result);
          setError("");
        }
      } catch (loadError) {
        if (isMounted) {
          setError(
            `Não foi possível carregar os produtos. ${
              loadError instanceof Error ? loadError.message : String(loadError)
            }`,
          );
        }
      } finally {
        if (isMounted) setLoading(false);
      }
    }

    load();
    return () => {
      isMounted = false;
    };
  }, []);

  const filteredProducts = useMemo(() => {
    const query = search.trim().toLocaleLowerCase("pt-BR");
    return products.filter((product) => {
      const matchesCategory =
        category === "Todos" || getProductCategory(product) === category;
      const searchable =
        `${getProductName(product)} ${getProductDescription(product)}`.toLocaleLowerCase(
          "pt-BR",
        );
      return matchesCategory && searchable.includes(query);
    });
  }, [category, products, search]);

  const closeForm = () => {
    setIsFormOpen(false);
    setEditingProduct(null);
    setForm(emptyForm);
  };

  const startEditing = (product) => {
    setIsFormOpen(true);
    setEditingProduct(product);
    setForm({
      name_product: getProductName(product),
      description_product: getProductDescription(product),
      price_product: String(getProductPrice(product)),
      category_products: getProductCategory(product),
      status: product.status ?? true,
    });
    setError("");
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    if (!form.name_product.trim()) {
      setError("Informe o nome do produto.");
      return;
    }
    const price = Number(form.price_product);
    if (!Number.isFinite(price) || price < 0) {
      setError("Informe um preço válido, igual ou maior que zero.");
      return;
    }

    const productData = {
      name_product: form.name_product.trim(),
      description_product: form.description_product.trim(),
      price_product: price,
      category_products: form.category_products,
      status: form.status,
    };

    setSaving(true);
    setError("");
    try {
      if (editingProduct) {
        await atualizarProduto(
          editingProduct.idProduct ?? editingProduct.id,
          productData,
        );
      } else {
        await criarProduto(productData);
      }
    } catch (saveError) {
      setError(
        `Não foi possível salvar o produto. ${
          saveError instanceof Error ? saveError.message : String(saveError)
        }`,
      );
      return;
    } finally {
      setSaving(false);
    }

    closeForm();
    try {
      setProducts(await fetchProducts());
      setError("");
    } catch (loadError) {
      setError(
        `Produto salvo, mas não foi possível atualizar a lista. ${
          loadError instanceof Error ? loadError.message : String(loadError)
        }`,
      );
    }
  };

  const handleDelete = async (product) => {
    const productId = product.idProduct ?? product.id;
    if (productId == null) {
      setError(
        "Não foi possível excluir: o produto não possui um identificador.",
      );
      return;
    }
    if (!window.confirm(`Excluir "${getProductName(product)}" do cardápio?`)) {
      return;
    }

    setError("");
    try {
      await excluirProduto(productId);
      setProducts((current) =>
        current.filter((item) => (item.idProduct ?? item.id) !== productId),
      );
    } catch (deleteError) {
      setError(
        `Não foi possível excluir o produto. ${
          deleteError instanceof Error
            ? deleteError.message
            : String(deleteError)
        }`,
      );
    }
  };

  const openNewProductForm = () => {
    setIsFormOpen(true);
    setEditingProduct(null);
    setForm(emptyForm);
    setError("");
  };

  return (
    <section className="section admin-page">
      <header className="admin-topbar">
        <div className="admin-brand">
          <span className="admin-brand-mark" aria-hidden="true">
            E
          </span>
          <div>
            <strong>Área administrativa</strong>
            <span>Gerenciamento do cardápio</span>
          </div>
        </div>
        <div className="admin-topbar-actions">
          <button
            className="primary-button"
            onClick={openNewProductForm}
            disabled={saving}
          >
            <span aria-hidden="true">＋</span> Novo produto
          </button>
        </div>
      </header>

      <div className="admin-heading">
        <div>
          <p className="eyebrow">Painel de gerenciamento</p>
          <h1>Administração</h1>
          <p className="admin-subtitle">
            Gerencie os produtos que aparecem no cardápio do restaurante.
          </p>
        </div>
      </div>

      <div className="admin-summary" aria-label="Resumo dos produtos">
        <div className="admin-summary-card">
          <span className="admin-summary-icon" aria-hidden="true">
            ▤
          </span>
          <div>
            <span>Produtos cadastrados</span>
            <strong>{products.length}</strong>
          </div>
        </div>
        <div className="admin-summary-card">
          <span className="admin-summary-icon" aria-hidden="true">
            ◈
          </span>
          <div>
            <span>Categorias no cardápio</span>
            <strong>{productCategories.length}</strong>
          </div>
        </div>
      </div>

      <div className="admin-toolbar">
        <div className="category-tabs" aria-label="Filtrar por categoria">
          {["Todos", ...productCategories].map((item) => (
            <button
              key={item}
              className={category === item ? "selected" : ""}
              aria-pressed={category === item}
              onClick={() => setCategory(item)}
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
            onChange={(event) => setSearch(event.target.value)}
            placeholder="Buscar produto"
          />
        </label>
      </div>

      {error && (
        <p className="admin-alert" role="alert">
          {error}
        </p>
      )}

      {loading ? (
        <p className="admin-feedback" role="status">
          Carregando produtos...
        </p>
      ) : filteredProducts.length ? (
        <div className="admin-product-list">
          {filteredProducts.map((product) => {
            const productId = product.idProduct ?? product.id;
            return (
              <article className="admin-product-card" key={productId}>
                <div className="admin-product-info">
                  <div className="admin-product-meta">
                    <span className="admin-product-category">
                      {getProductCategory(product)}
                    </span>
                    <span
                      className={`admin-product-status ${
                        product.status === false ? "inactive" : "active"
                      }`}
                    >
                      {product.status === false ? "Inativo" : "Ativo"}
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
                    onClick={() => startEditing(product)}
                    disabled={saving}
                  >
                    Editar
                  </button>
                  <button
                    className="admin-delete-button"
                    onClick={() => handleDelete(product)}
                    disabled={saving}
                  >
                    Excluir
                  </button>
                </div>
              </article>
            );
          })}
        </div>
      ) : (
        <div className="empty-state admin-empty-state">
          <span aria-hidden="true">◌</span>
          <h3>Nenhum produto encontrado</h3>
          <p>
            {products.length
              ? "Tente outra busca ou categoria."
              : "Cadastre o primeiro produto para começar."}
          </p>
          {search || category !== "Todos" ? (
            <button
              className="secondary-button"
              onClick={() => {
                setSearch("");
                setCategory("Todos");
              }}
            >
              Limpar filtros
            </button>
          ) : null}
        </div>
      )}

      {isFormOpen ? (
        <div
          className="admin-modal-backdrop"
          role="presentation"
          onMouseDown={(event) => {
            if (event.target === event.currentTarget && !saving) closeForm();
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
                  {editingProduct ? "Atualização do cardápio" : "Novo cadastro"}
                </p>
                <h2 id="admin-form-title">
                  {editingProduct ? "Editar produto" : "Novo produto"}
                </h2>
              </div>
              <button
                type="button"
                className="admin-modal-close"
                aria-label="Fechar formulário"
                onClick={closeForm}
                disabled={saving}
              >
                ×
              </button>
            </div>
            <form className="admin-form" onSubmit={handleSubmit}>
              <label>
                Nome do produto
                <input
                  required
                  maxLength={120}
                  value={form.name_product}
                  onChange={(event) =>
                    setForm({ ...form, name_product: event.target.value })
                  }
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
                    setForm({
                      ...form,
                      description_product: event.target.value,
                    })
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
                      setForm({ ...form, price_product: event.target.value })
                    }
                  />
                </label>
                <label>
                  Categoria
                  <select
                    required
                    value={form.category_products}
                    onChange={(event) =>
                      setForm({
                        ...form,
                        category_products: event.target.value,
                      })
                    }
                  >
                    {productCategories.map((item) => (
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
                  onChange={(event) =>
                    setForm({ ...form, status: event.target.checked })
                  }
                />
                Produto ativo no cardápio
              </label>
              <div className="admin-form-actions">
                <button
                  type="button"
                  className="secondary-button"
                  onClick={closeForm}
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
      ) : null}
    </section>
  );
}

export default AdminPage;
