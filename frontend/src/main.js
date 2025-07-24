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

  
  document.addEventListener('DOMContentLoaded', function() {
    const mobileToggle = document.querySelector('.mobile-toggle');
    const mainNav = document.querySelector('.main-nav');
    
    if(mobileToggle && mainNav) {
      mobileToggle.addEventListener('click', () => {
        mainNav.classList.toggle('active');
      });
    }
    
    document.addEventListener('touchstart', function(event) {
      if (event.target.tagName === 'INPUT') {
        event.target.style.fontSize = '16px';
      }
    }, { passive: true });
  });