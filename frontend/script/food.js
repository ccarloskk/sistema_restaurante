const OrdersItemsDetails = "http://localhost:8080/order_item/details";

getOrdersItems();

async function getOrdersItems() {
    try {
        const response = await fetch(OrdersItemsDetails);

        if (!response.ok) {
            throw new Error("Erro ao buscar dados do pedido");
        }

        const items = await response.json();
        loadOrder(items);

    } catch (error) {
        console.error(error);
    }
}

function loadOrder(items) {
    const container = document.getElementById("ordersContainer");
    container.innerHTML = "";

    if (!items || items.length === 0) {
        container.innerHTML = "<p>Nenhum pedido encontrado</p>";
        return;
    }

    const ordersGrouped = {};

    items.forEach(item => {
        if (!ordersGrouped[item.id_order]) {
            ordersGrouped[item.id_order] = [];
        }
        ordersGrouped[item.id_order].push(item);
    });

    Object.values(ordersGrouped).forEach(orderItems => {
        const order = orderItems[0];

        const section = document.createElement("section");
        section.classList.add("order-card");

        section.innerHTML = `
            <div class="order-header">
                <span class="order-number">Ordem do pedido #${order.id_items_order}</span>
            </div>

            <div class="order-info">
                <p><strong>Comanda:</strong> #${order.id_order}</p>
                <p><strong>Cliente:</strong> ${order.name_client}</p>
            </div>

            <div class="orderContainer">
                <h3>Itens do Pedido</h3>
                <ul></ul>
            </div>

            <div class="order-status">
                <span class="status"></span>
            </div>
        `;

        const ul = section.querySelector("ul");

        orderItems.forEach(item => {
            const li = document.createElement("li");
            li.textContent = `${item.name_prod} (${item.quantity}x)`;
            ul.appendChild(li);
        });

        container.appendChild(section);
    });
}


