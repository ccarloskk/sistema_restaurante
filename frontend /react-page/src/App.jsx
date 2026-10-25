import { useEffect, useState } from "react";
import "./App.css";
import CartDrawer from "./components/CartDrawer";
import { formatPrice, menuItems } from "./data/menu";
import HomePage from "./pages/HomePage";
import MenuPage from "./pages/MenuPage";
import { createWhatsAppLink, restaurant } from "./data/restaurant";

function App() {
  const [route, setRoute] = useState(
    window.location.hash.replace("#/", "") || "inicio",
  );
  const [items, setItems] = useState([]);
  const [menuLoading, setMenuLoading] = useState(true);
  const [menuError, setMenuError] = useState("");
  const [cart, setCart] = useState([]);
  const [cartOpen, setCartOpen] = useState(false);
  const [notice, setNotice] = useState("");

  useEffect(() => {
    let isMounted = true;

    menuItems()
      .then((products) => {
        if (isMounted) setItems(products);
      })
      .catch((error) => {
        if (isMounted) {
          setMenuError(
            `Não foi possível carregar o cardápio. ${
              error instanceof Error ? error.message : String(error)
            }`,
          );
        }
      })
      .finally(() => {
        if (isMounted) setMenuLoading(false);
      });

    return () => {
      isMounted = false;
    };
  }, []);

  useEffect(() => {
    const onHashChange = () =>
      setRoute(window.location.hash.replace("#/", "") || "inicio");
    window.addEventListener("hashchange", onHashChange);
    return () => window.removeEventListener("hashchange", onHashChange);
  }, []);

  useEffect(() => {
    if (!notice) return undefined;
    const timeout = window.setTimeout(() => setNotice(""), 2400);
    return () => window.clearTimeout(timeout);
  }, [notice]);

  const goTo = (nextRoute) => {
    window.location.hash = `/${nextRoute}`;
    setCartOpen(false);
    window.scrollTo({ top: 0, behavior: "smooth" });
  };

  const addToCart = (item) => {
    setCart((current) => {
      const found = current.find((cartItem) => cartItem.id === item.id);
      if (found) {
        return current.map((cartItem) =>
          cartItem.id === item.id
            ? { ...cartItem, quantity: cartItem.quantity + 1 }
            : cartItem,
        );
      }
      return [...current, { ...item, quantity: 1 }];
    });
    setNotice(`${item.name} adicionado ao pedido`);
  };

  const changeQuantity = (id, amount) => {
    setCart((current) =>
      current.flatMap((item) =>
        item.id === id && item.quantity + amount <= 0
          ? []
          : item.id === id
            ? { ...item, quantity: item.quantity + amount }
            : item,
      ),
    );
  };

  const cartCount = cart.reduce((sum, item) => sum + item.quantity, 0);
  const cartTotal = cart.reduce(
    (sum, item) => sum + item.price * item.quantity,
    0,
  );
  const orderMessage = [
    `Olá! Gostaria de fazer um pedido na ${restaurant.name}:`,
    "",
    ...cart.map(
      (item) =>
        `${item.quantity}x ${item.name} — ${formatPrice(item.price * item.quantity)}`,
    ),
    "",
    `Total: ${formatPrice(cartTotal)}`,
    "",
    "Por favor, confirmem a disponibilidade. Obrigado!",
  ].join("\n");
  const pages = {
    inicio: (
      <HomePage
        items={items}
        isMenuLoading={menuLoading}
        menuError={menuError}
        onNavigate={goTo}
        onAdd={addToCart}
      />
    ),
    cardapio: (
      <MenuPage
        items={items}
        isMenuLoading={menuLoading}
        menuError={menuError}
        onAdd={addToCart}
      />
    ),
  };

  return (
    <div className="app-shell">
      <header className="site-header">
        <button
          className="brand"
          onClick={() => goTo("inicio")}
          aria-label="Ir para início"
        >
          <span className="brand-mark">E</span>
          <span>
            <span className="brand-name">{restaurant.name}</span>
            <span className="brand-caption">comida paraense em Araucária</span>
          </span>
        </button>
        <nav className="main-nav" aria-label="Navegação principal">
          <button
            className={route === "inicio" ? "active" : ""}
            onClick={() => goTo("inicio")}
          >
            Início
          </button>
          <button
            className={route === "cardapio" ? "active" : ""}
            onClick={() => goTo("cardapio")}
          >
            Cardápio
          </button>
        </nav>
        <button
          className="cart-button"
          onClick={() => setCartOpen(true)}
          aria-label={`Abrir carrinho com ${cartCount} itens`}
        >
          <span className="cart-icon" aria-hidden="true">
            ＋
          </span>{" "}
          Meu pedido <span className="cart-count">{cartCount}</span>
        </button>
      </header>
      <main>{pages[route] || pages.inicio}</main>
      <footer className="site-footer">
        <div className="footer-brand">
          <span className="brand-mark">E</span>
          <div>
            <strong>{restaurant.name}</strong>
            <small>comida paraense em Araucária</small>
          </div>
        </div>
        <div>
          <small>{restaurant.address}</small>
        </div>
        <div>
          <small>
            <a href={`tel:+${restaurant.phone}`}>{restaurant.displayPhone}</a>
          </small>
        </div>
        <a
          className="footer-order"
          href={createWhatsAppLink(
            `Olá! Gostaria de saber mais sobre o cardápio da ${restaurant.name}.`,
          )}
          target="_blank"
          rel="noreferrer"
        >
          Fale pelo WhatsApp <span>↗</span>
        </a>
      </footer>
      {cartOpen && (
        <CartDrawer
          cart={cart}
          total={cartTotal}
          whatsappHref={createWhatsAppLink(orderMessage)}
          onClose={() => setCartOpen(false)}
          onChange={changeQuantity}
        />
      )}
      {notice && (
        <div className="toast" role="status" aria-live="polite">
          {notice}
        </div>
      )}
    </div>
  );
}

export default App;
