// const ME_URL = 'http://localhost:8080/users/me';

// function getToken() {
//     return localStorage.getItem('token');
// }

// async function getUserData() {
//     const token = getToken();
//     if (!token) return null;

//     try {
//         const res = await fetch(ME_URL, {
//             headers: { 'Authorization': `Bearer ${token}` }
//         });

//         console.log('ME status:', res.status);

//         if (res.status === 401) {
//             localStorage.removeItem('token');
//             return null;
//         }

//         if (!res.ok) {
//             console.error('Erro ao buscar dados do usuário:', res.status);
//             return null;
//         }

//         return await res.json();
//     } catch (error) {
//         console.error('Erro na requisição getUserData:', error);
//         return null;
//     }
// }

// async function requireAdmin() {
//     const token = getToken();
    
//     if (!token) {
//         alert('Você precisa estar logado para acessar esta página');
//         window.location.href = '/frontend/page/login.html';
//         return false;
//     }

//     const userData = await getUserData();

//     if (!userData) {
//         alert('Sessão expirada. Faça login novamente.');
//         localStorage.removeItem('token');
//         window.location.href = '/frontend/page/login.html';
//         return false;
//     }

//     if (userData.role !== 'ADMIN') {
//         alert('Acesso negado! Apenas administradores podem acessar esta página.');
//         window.location.href = '/frontend/page/403.html';
//         return false;
//     }

//     return true;
// }

// async function fetchWithAuth(url, options = {}) {
//     const token = getToken();

//     const headers = {
//         'Content-Type': 'application/json',
//         ...options.headers
//     };

//     if (token) {
//         headers['Authorization'] = `Bearer ${token}`;
//     }

//     try {
//         const response = await fetch(url, {
//             ...options,
//             headers
//         });
// x
//         if (response.status === 403) {
//             alert('Acesso negado! Você não tem permissão para esta ação.');
//             window.location.href = '/frontend/page/403.html';
//             return null;
//         }

//         if (response.status === 401) {
//             alert('Sessão expirada. Faça login novamente.');
//             localStorage.removeItem('token');
//             window.location.href = '/frontend/page/login.html';
//             return null;
//         }

//         return response;
//     } catch (error) {
//         console.error('Erro na requisição:', error);
//         throw error;
//     }
// }