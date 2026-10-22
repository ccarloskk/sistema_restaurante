import { createWhatsAppLink, restaurant } from "../data/restaurant";
import MenuCard from "../components/MenuCard";
import "./HomePage.css";

function HomePage({ items, isMenuLoading, menuError, onNavigate, onAdd }) {
  return (
    <>
      <section className="hero-section">
        <div className="hero-copy">
          <p className="eyebrow">Sabores do Pará em Araucária</p>
          <h1>
            Estação
            <br />
            <em>Paraense.</em>
          </h1>
          <p className="hero-text">
            Comida paraense feita com carinho, tempero e tradição. Confira o
            cardápio e mande seu pedido direto para a gente pelo WhatsApp.
          </p>
          <div className="hero-actions">
            <button
              className="primary-button"
              onClick={() => onNavigate("cardapio")}
            >
              Ver cardápio <span aria-hidden="true">→</span>
            </button>
            <a
              className="secondary-button"
              href={createWhatsAppLink(
                `Olá! Gostaria de fazer um pedido na ${restaurant.name}.`,
              )}
              target="_blank"
              rel="noreferrer"
            >
              Pedir pelo WhatsApp
            </a>
          </div>
        </div>
        <aside
          className="restaurant-card"
          aria-label="Informações do restaurante"
        >
          <p className="eyebrow">Visite a gente</p>
          <h2>{restaurant.name}</h2>
          <p>Restaurante paraense em Fazenda Velha, Araucária — Paraná.</p>
          <a
            className="restaurant-rating"
            href={restaurant.googleUrl}
            target="_blank"
            rel="noreferrer"
            aria-label={`Avaliação ${restaurant.rating} de 5, ${restaurant.reviewCount} avaliações no Google`}
          >
            <strong>{restaurant.rating}</strong>
            <span>
              ★★★★★ &nbsp; {restaurant.reviewCount} avaliações no Google
            </span>
          </a>
          <ul className="restaurant-details">
            <li>
              <strong>Endereço</strong>
              <a href={restaurant.mapsUrl} target="_blank" rel="noreferrer">
                {restaurant.address}
              </a>
            </li>
            <li>
              <strong>Telefone / WhatsApp</strong>
              <a href={`tel:+${restaurant.phone}`}>{restaurant.displayPhone}</a>
            </li>
            <li>
              <strong>Opções de serviço</strong>
              Mesas externas · Permite a entrada de cães
            </li>
          </ul>
        </aside>
      </section>
      <section
        className="feature-strip"
        aria-label="Informações do restaurante"
      >
        <div className="feature-item">
          <div>
            <strong>Avaliação 5,0 no Google</strong>
            <span>Com base em 2 avaliações</span>
          </div>
        </div>
        <div className="feature-item">
          <div>
            <strong>Mesas externas</strong>
            <span>Espaço ao ar livre</span>
          </div>
        </div>
        <div className="feature-item">
          <div>
            <strong>Pet friendly</strong>
            <span>Cães são bem-vindos</span>
          </div>
        </div>
      </section>
      <section className="section featured-section">
        <div className="section-heading">
          <div>
            <p className="eyebrow">Conheça nossos pratos</p>
            <h2>Cardápio</h2>
          </div>
          <button
            className="text-button"
            onClick={() => onNavigate("cardapio")}
          >
            Ver todos os pratos <span aria-hidden="true">→</span>
          </button>
        </div>
        <div className="menu-grid">
          {isMenuLoading ? (
            <p role="status">Carregando cardápio...</p>
          ) : menuError ? (
            <p role="alert">{menuError}</p>
          ) : (
            items
              .slice(0, 4)
              .map((item) => (
                <MenuCard key={item.id} item={item} onAdd={onAdd} />
              ))
          )}
        </div>
      </section>
    </>
  );
}

export default HomePage;
