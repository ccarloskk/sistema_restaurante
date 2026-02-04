document.addEventListener("DOMContentLoaded", () => {
    const params = new URLSearchParams(window.location.search);
    const idOrder = params.get("id_order");

    if (!idOrder) {
        console.error("ID da comanda não encontrado");
        return;
    }

    const itemsUrl = `http://localhost:8080/order_item/${idOrder}`;
    const clientUrl = `http://localhost:8080/orders/details/${idOrder}`;

    loadOrder(itemsUrl, clientUrl);
});

async function loadOrder(itemsUrl, clientUrl) {
    try {
        const [itemsResponse, clientResponse] = await Promise.all([
            fetch(itemsUrl),
            fetch(clientUrl)
        ]);

        if (!itemsResponse.ok || !clientResponse.ok) {
            throw new Error("Erro ao buscar dados do pedido");
        }

        const items = await itemsResponse.json();
        const client = await clientResponse.json();
        
        renderOrder(items, client);

    } catch (error) {
        console.error(error);
    }
}

function renderOrder(items, client) {
    const orderList = document.getElementById("orderList");
    const orderTotal = document.getElementById("orderTotal");
    const clientName = document.getElementById("clientName");
    const orderStatus = document.getElementById("orderStatus");

    orderList.innerHTML = "";

    if (!items || items.length === 0) {
        orderList.innerHTML = "<li>Nenhum item encontrado</li>";
        orderTotal.textContent = "Total: R$ 0,00";
        return;
    }

    clientName.textContent = client[0].name_customer;
    orderStatus.textContent = client[0].status;
    
    const total = Number(items[0].total || 0);
    orderTotal.textContent = `Total: R$ ${total.toFixed(2)}`;

    items.forEach(item => {
        const li = document.createElement("li");

        li.innerHTML = `
            <span>${item.name_product}</span>
            <span>${item.quantity}x</span>
        `;

        orderList.appendChild(li);
    });
}
detailsClient(clientUrl);



