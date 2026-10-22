import { useEffect } from "react";
import { formatPrice } from "../data/menu";
import "./CartDrawer.css";

function CartDrawer({ cart, total, whatsappHref, onClose, onChange }) {
  useEffect(() => {
    const closeOnEscape = (event) => {
      if (event.key === "Escape") onClose();
    };
    window.addEventListener("keydown", closeOnEscape);
    return () => window.removeEventListener("keydown", closeOnEscape);
  }, [onClose]);

  return (
    <div className="drawer-overlay" onMouseDown={onClose}>
      <aside
        className="cart-drawer"
        role="dialog"
        aria-modal="true"
        aria-labelledby="cart-title"
        onMouseDown={(event) => event.stopPropagation()}
      >
        <div className="drawer-header">
          <div>
            <p className="eyebrow">Sua seleção</p>
            <h2 id="cart-title">Meu pedido</h2>
          </div>
          <button
            className="close-button"
            onClick={onClose}
            aria-label="Fechar pedido"
          >
            ×
          </button>
        </div>
        {cart.length ? (
          <>
            <div className="cart-items">
              {cart.map((item) => (
                <div className="cart-item" key={item.id}>
                  <div>
                    <h3>{item.name}</h3>
                    <strong>{formatPrice(item.price)}</strong>
                    <div className="quantity">
                      <button
                        onClick={() => onChange(item.id, -1)}
                        aria-label={`Remover uma unidade de ${item.name}`}
                      >
                        −
                      </button>
                      <span>{item.quantity}</span>
                      <button
                        onClick={() => onChange(item.id, 1)}
                        aria-label={`Adicionar uma unidade de ${item.name}`}
                      >
                        +
                      </button>
                    </div>
                  </div>
                </div>
              ))}
            </div>
            <div className="drawer-footer">
              <div>
                <span>Total</span>
                <strong>{formatPrice(total)}</strong>
              </div>
              <a
                className="primary-button full-button"
                href={whatsappHref}
                target="_blank"
                rel="noreferrer"
              >
                Enviar pedido pelo WhatsApp <span aria-hidden="true">↗</span>
              </a>
              <p className="whatsapp-note">
                O WhatsApp abrirá com os itens e o total para você confirmar o
                pedido.
              </p>
            </div>
          </>
        ) : (
          <div className="empty-cart">
            <span className="empty-cart-label">Estação Paraense</span>
            <h3>Seu pedido está vazio</h3>
            <p>Escolha seus pratos favoritos no cardápio para começar.</p>
          </div>
        )}
      </aside>
    </div>
  );
}

export default CartDrawer;
