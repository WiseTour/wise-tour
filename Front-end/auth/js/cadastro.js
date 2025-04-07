
function cadastrar() {
    // aguardar();

    //Recupere o valor da nova input pelo nome do id
    // Agora vá para o método fetch logo abaixo
    var nomeCompletoVar = nome_completo_input.value;
    var emailVar = email_input.value;
    var numeroUsuarioVar = numero_usuario_input.value;

    // Verificando se há algum campo em branco
    if (
      nomeCompletoVar == "" ||
      emailVar == "" ||
      numeroUsuarioVar == ""
    ) {
      cardErro.style.display = "block";
      mensagem_erro.innerHTML =
        "(Mensagem de erro para todos os campos em branco)";

      // finalizarAguardar();
      return false;
    } else {
      setInterval(sumirMensagem, 5000);
    }

    fetch("/usuarios/cadastrar", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        // crie um atributo que recebe o valor recuperado aqui
        // Agora vá para o arquivo routes/usuario.js
        nomeServer: nomeCompletoVar,
        emailServer: emailVar,
        numeroUsuarioServer: numeroUsuarioVar,
      }),
    })
      .then(function (resposta) {
        console.log("resposta: ", resposta);

        if (resposta.ok) {
          cardErro.style.display = "block";

          mensagem_erro.innerHTML =
            "Cadastro realizado com sucesso! Redirecionando para tela de Login...";

          setTimeout(() => {
            window.location = "login.html";
          }, "2000");

          limparFormulario();
          // finalizarAguardar();
        } else {
          throw "Houve um erro ao tentar realizar o cadastro!";
        }
      })
      .catch(function (resposta) {
        console.log(`#ERRO: ${resposta}`);
        // finalizarAguardar();
      });

    return false;
  }

  function sumirMensagem() {
    cardErro.style.display = "none";
  }
