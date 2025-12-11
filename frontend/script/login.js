async function loginUser(event) {
  event.preventDefault(); 

  const email = document.getElementById('email').value.trim();
  const password = document.getElementById('password').value;
  const roleSelected = document.getElementById('role').value; 

  if (!email || !password || !roleSelected) {
    alert('Preencha todos os campos!');
    return;
  }

  try {
    const response = await fetch('http://localhost:8080/auth/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        email: email,
        password: password,
        role: roleSelected 
      })
    });

    if (!response.ok) {
      const errorText = await response.text();
      alert(errorText || 'E-mail, senha ou tipo de usuário inválidos!');
      return;
    }

    const data = await response.json();

    if (!data.token) {
      alert('Erro: resposta do servidor não contém token.');
      console.error('Resposta do servidor:', data);
      return;
    }

    localStorage.setItem('token', data.token);

    if (data.user) {
      localStorage.setItem('user', JSON.stringify(data.user));
    }

    let role = null;

    if (data.user && data.user.role) {
      role = data.user.role;
    }

    if (!role && data.role) {
      role = data.role;
    }

    if (!role) {
      role = roleSelected;
    }

    if (!role) {
      alert('Erro: não foi possível identificar o tipo de usuário (role).');
      console.error('Resposta do servidor sem role:', data);
      return;
    }

    role = role.toUpperCase();

    alert('Login realizado com sucesso!');

    if (role === 'ADMIN') {
      window.location.href = '/frontend/page/admin.html';   
    } else if (role === 'GARCOM') {
      window.location.href = '/page/garcom.html'; 
    } else {
      alert('Tipo de usuário não reconhecido: ' + role);
    }

  } catch (error) {
    console.error('Erro ao fazer login:', error);
    alert('Erro ao conectar com o servidor. Tente novamente.');
  }
}