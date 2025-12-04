async function loginUser(event){

    const email = document.getElementById("email");
    const password = document.getElementById("password");

    const response = await fetch("http://localhost:8081/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({ email, password })
      });
}

function logoutUser(event){
}
