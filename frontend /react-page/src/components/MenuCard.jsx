import { formatPrice } from "../data/menu";
import "./MenuCard.css";

function MenuCard({ item, onAdd }) {
  return (
    <article className="menu-card">
      <div className="dish-label">
        <strong>{item.category}</strong>
        {item.tag && <small>{item.tag}</small>}
      </div>
      <div className="dish-info">
        <h3>{item.name}</h3>
        <p>{item.description}</p>
        <div className="dish-bottom">
          <strong>{formatPrice(item.price)}</strong>
          <button
            className="add-button"
            onClick={() => onAdd(item)}
            aria-label={`Adicionar ${item.name} ao pedido`}
          >
            <span aria-hidden="true">+</span> Adicionar
          </button>
        </div>
      </div>
    </article>
  );
}

export default MenuCard;
