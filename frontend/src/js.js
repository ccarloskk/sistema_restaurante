function validarFormulario() {
    const nome = document.getElementById("nome").value.trim();
    const email = document.getElementById("email").value.trim();
    const telefone = document.getElementById("telefone").value.trim();
  
    if (nome.length < 3) {
      alert("Nome deve ter pelo menos 3 caracteres.");
      return false;
    }
  
    if (!email.includes("@")) {
      alert("Email inválido.");
      return false;
    }
  
    if (telefone.length < 10) {
      alert("Telefone deve ter pelo menos 10 dígitos.");
      return false;
    }
  
    return true;
  }
  
  function mostrarDados() {
    const params = new URLSearchParams(window.location.search);
    const nome = params.get("nome");
    const email = params.get("email");
    const telefone = params.get("telefone");
    const data = params.get("data");
    const hora = params.get("hora");
    const pessoas = params.get("pessoas");
  
    const container = document.getElementById("dadosReserva");
    container.innerHTML = `
      <p><strong>Nome:</strong> ${nome}</p>
      <p><strong>Email:</strong> ${email}</p>
      <p><strong>Telefone:</strong> ${telefone}</p>
      <p><strong>Data:</strong> ${data}</p>
      <p><strong>Hora:</strong> ${hora}</p>
      <p><strong>Pessoas:</strong> ${pessoas}</p>
    `;
  }
  