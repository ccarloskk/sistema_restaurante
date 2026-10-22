export const restaurant = {
  name: "Estação Paraense",
  address:
    "Rua Olga Cantador Incot, 576 - Fazenda Velha, Araucária - PR, 83704-584",
  phone: "5541996520121",
  rating: "5,0",
  reviewCount: 2,
  googleUrl:
    "https://www.google.com/search?q=Esta%C3%A7%C3%A3o+Paraense+Arauc%C3%A1ria",
  mapsUrl:
    "https://www.google.com/maps/search/?api=1&query=Rua+Olga+Cantador+Incot%2C+576%2C+Arauc%C3%A1ria+-+PR",
};

export const createWhatsAppLink = (message) =>
  `https://wa.me/${restaurant.phone}?text=${encodeURIComponent(message)}`;
