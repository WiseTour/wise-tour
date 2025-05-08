function cadastrarResponsavel() {

  var nomeResponsavelVar = responsavel_nome_input.value;
  var cargoVar = responsavel_cargo_input.value;
  var telefoneVar = responsavel_telefone_input.value;
  var cnpjEmpresaVar = responsavel_cnpj_empresa_input.value;
  var idInformacaoVar = responsavel_id_informacao_input.value;
  var siglaUfVar = responsavel_sigla_uf_input.value;

  // Verificando se há algum campo em branco
  if (
    nomeResponsavelVar == "" ||
    cargoVar == "" ||
    telefoneVar == "" ||
    cnpjEmpresaVar == "" ||
    idInformacaoVar == "" ||
    siglaUfVar == ""
  ) {
    alert("Mensagem de erro para todos os campos em branco");
    return false;
  }

  fetch("/usuarios/cadastrarResponsavel", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      nomeServer: nomeResponsavelVar,
      cargoServer: cargoVar,
      telefoneServer: telefoneVar,
      cnpjEmpresaServer: cnpjEmpresaVar,
      idInformacaoServer: idInformacaoVar,
      siglaUfServer: siglaUfVar
    }),
  })
    .then(function (resposta) {
      console.log("resposta: ", resposta);

      if (resposta.ok) {
        alert ("Cadastro Responsavel realizado!!!")

        limparFormulario();
      } else {
        throw "Houve um erro ao tentar realizar o cadastro!";
      }
    })
    .catch(function (resposta) {
      console.log(`#ERRO: ${resposta}`);
    });

  return false;
}
