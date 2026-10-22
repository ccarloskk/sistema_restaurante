import { useMemo, useState } from "react";
import MenuCard from "../components/MenuCard";
import "./MenuPage.css";
import { categories } from "../data/menu";

function MenuPage({ items, isMenuLoading, menuError, onAdd }) {
  const [category, setCategory] = useState("Todos");
  const [search, setSearch] = useState("");
  const filteredItems = useMemo(
    () =>
      items.filter((item) => {
        const matchesCategory =
          category === "Todos" || item.category === category;
        const searchable = `${item.name} ${item.description}`.toLowerCase();
        return matchesCategory && searchable.includes(search.toLowerCase());
      }),
    [category, items, search],
  );

  return (
    <section className="section menu-page">
      <div className="menu-heading">
        <div>
          <p className="eyebrow">Receitas do Pará em Araucária</p>
          <h1>Cardápio</h1>
        </div>
        <p>
          Escolha o que vai deixar sua refeição mais gostosa.
          <br />
          Para filtrar, selecione uma categoria ou busque pelo nome.
        </p>
      </div>
      <div className="menu-toolbar">
        <div className="category-tabs" aria-label="Filtrar por categoria">
          {categories.map((item) => (
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
            aria-label="Buscar pratos no cardápio"
            value={search}
            onChange={(event) => setSearch(event.target.value)}
            placeholder="Buscar no cardápio"
          />
        </label>
      </div>
      {isMenuLoading ? (
        <p role="status">Carregando cardápio...</p>
      ) : menuError ? (
        <p role="alert">{menuError}</p>
      ) : filteredItems.length ? (
        <div className="menu-grid">
          {filteredItems.map((item) => (
            <MenuCard key={item.id} item={item} onAdd={onAdd} />
          ))}
        </div>
      ) : (
        <div className="empty-state">
          <span>◌</span>
          <h3>Não encontramos esse prato</h3>
          <p>Tente buscar por outro termo ou categoria.</p>
          <button
            className="secondary-button"
            onClick={() => {
              setSearch("");
              setCategory("Todos");
            }}
          >
            Limpar filtros
          </button>
        </div>
      )}
    </section>
  );
}

export default MenuPage;
