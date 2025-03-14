// sessão
function validarSessao() {
    var email = sessionStorage.EMAIL_USUARIO;
    var nome = sessionStorage.NOME_USUARIO;

    var b_usuario = document.getElementById("b_usuario");

    if (email != null && nome != null) {
        b_usuario.innerHTML = nome;
    } else {
        window.location = "../login.html";
    }
}

function redirecionar(tela) {
    window.location.href = tela + ".html"
}

function mudarVisualisacaoSenha(atual) { 
    const inpSenha = document.getElementById('inp_senha')
    const imgEyeOpen = document.getElementById('img_eye_open')
    const imgEyeClosed = document.getElementById('img_eye_closed')

    if (atual == 'closed') {
        inpSenha.type = 'text'
        imgEyeOpen.style.display = 'flex'
        imgEyeClosed.style.display = 'none'
    } else {
        inpSenha.type = 'password'
        imgEyeOpen.style.display = 'none'
        imgEyeClosed.style.display = 'flex'
    }
} 

function limparSessao() {
    sessionStorage.clear();
    window.location = "../login.html";
}

// carregamento (loading)
function aguardar() {
    var divAguardar = document.getElementById("div_aguardar");
    divAguardar.style.display = "flex";
}

function finalizarAguardar(texto) {
    var divAguardar = document.getElementById("div_aguardar");
    divAguardar.style.display = "none";

    var divErrosLogin = document.getElementById("div_erros_login");
    if (texto) {
        divErrosLogin.style.display = "flex";
        divErrosLogin.innerHTML = texto;
    }
}

