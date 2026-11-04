import { useEffect, useMemo, useState } from "react";
import {
  atualizarProduto,
  buscarProdutos,
  criarProduto,
  excluirProduto,
} from "../admin/products";
import { categories } from "../data/menu";
import ProductFilters from "./ProductFilters";
import ProductForm from "./ProductForm";
import ProductList from "./ProductList";
import ProductSummary from "./ProductSummary";
import {
  getProductCategory,
  getProductDescription,
  getProductName,
  getProductPrice,
  getProductStatus,
} from "./productUtils";
import "./AdminPage.css";

const productCategories = categories.filter((category) => category !== "Todos");
const emptyForm = {
  name_product: "",
  description_product: "",
  price_product: "",
  category_products: productCategories[0] ?? "",
  status: true,
};

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

    async function loadProducts() {
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

    loadProducts();
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

  const updateFormField = (field, value) => {
    setForm((currentForm) => ({ ...currentForm, [field]: value }));
  };

  const startEditing = (product) => {
    setIsFormOpen(true);
    setEditingProduct(product);
    setForm({
      name_product: getProductName(product),
      description_product: getProductDescription(product),
      price_product: String(getProductPrice(product)),
      category_products: getProductCategory(product),
      status: getProductStatus(product),
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
      statusProduct: form.status,
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
      setProducts((currentProducts) =>
        currentProducts.filter(
          (item) => (item.idProduct ?? item.id) !== productId,
        ),
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

      <ProductSummary
        productCount={products.length}
        categoryCount={productCategories.length}
      />

      <ProductFilters
        categories={productCategories}
        selectedCategory={category}
        onCategoryChange={setCategory}
        search={search}
        onSearchChange={setSearch}
      />

      {error && (
        <p className="admin-alert" role="alert">
          {error}
        </p>
      )}

      <ProductList
        loading={loading}
        products={filteredProducts}
        totalProducts={products.length}
        hasFilters={Boolean(search || category !== "Todos")}
        saving={saving}
        onEdit={startEditing}
        onDelete={handleDelete}
        onClearFilters={() => {
          setSearch("");
          setCategory("Todos");
        }}
      />

      {isFormOpen && (
        <ProductForm
          form={form}
          isEditing={Boolean(editingProduct)}
          saving={saving}
          categories={productCategories}
          onChange={updateFormField}
          onSubmit={handleSubmit}
          onClose={closeForm}
        />
      )}
    </section>
  );
}

export default AdminPage;
