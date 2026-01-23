async function getOrders() {
    const UrlOrders = "http://localhost:8080/orders/details";
    try {
        const response = await fetch(UrlOrders);

        if (!response.ok) {
            throw new Error("Erro ao buscar as comandas");
        }

        return await response.json();
    } catch (error) {
        console.error("Erro ao buscar comandas:", error);
        return [];
    }
}

async function carregarComandas() {
    const lista = document.getElementById("listaComandas");
    const emptyState = document.getElementById("emptyState");
    if (!lista || !emptyState) {
        console.error("listaComandas ou emptyState não encontrados no HTML");
        return;
    }
    lista.innerHTML = "";
    const comandas = await getOrders();
    if (comandas.length === 0) {
        emptyState.classList.add("active");
        return;
    }
    emptyState.classList.remove("active");
    comandas.forEach(comanda => {
        lista.innerHTML += `
            <div class="comanda-item">
                <div>
                    <strong>Comanda #${comanda.id_order}</strong>
                    <p>Cliente: ${comanda.name_customer}</p>
                </div>
                <div>
                    <p>Status: 
                        <span class="status ${comanda.status === "ABERTA" ? "aberta" : "fechada"}">
                            ${comanda.status}
                        </span>
                    </p>
                    <p>Total: R$ ${Number(comanda.total).toFixed(2)}</p>
                </div>
            </div>
        `;
    });
}
document.addEventListener("DOMContentLoaded", () => {
    carregarComandas();
});

getOrders();
