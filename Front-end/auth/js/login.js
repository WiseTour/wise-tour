
function entrar() {
    var emailVar = document.getElementById('email_input').value;
    var senhaVar = document.getElementById('senha_input').value;

    if (emailVar == "" || senhaVar == "") {
      document.getElementById('cardErro').style.display = "block";
      document.getElementById('mensagem_erro').innerHTML = "(Mensagem de erro para todos os campos em branco)";
      return false;
    } else {
      setInterval(sumirMensagem, 5000);
    }

    console.log("FORM LOGIN: ", emailVar);
    console.log("FORM SENHA: ", senhaVar);

    fetch("/usuarios/autenticar", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        emailServer: emailVar,
        senhaServer: senhaVar
      })
    }).then(function (resposta) {
      console.log("ESTOU NO THEN DO entrar()!");

      if (resposta.ok) {
        resposta.json().then(json => {
          console.log(json);

          sessionStorage.setItem("EMAIL_USUARIO", json.email);
          sessionStorage.setItem("ID_USUARIO", json.idLogin);

          setTimeout(function () {
            window.location = "./dashboard.html";
          }, 1000);
        });
      } else {
        console.log("Houve um erro ao tentar realizar o login!");
        resposta.text().then(texto => {
          console.error(texto);
        });
      }

    }).catch(function (erro) {
      console.log(erro);
    });

    return false;
  }

  function sumirMensagem() {
    document.getElementById('cardErro').style.display = "none";
  }
