<h1>Calculadora de Saldo Bancario-LetsCodeM</h1>

<p>Projeto final do modulo Java-Dependências externas do curso Full-Stack organizado pelo Grupo Santander em parceria com a Let's Code</p>

> Status: Developing ⚠️

## Challenge

<p>
  Projeto: Calculadora de "saldo bancário" com base num arquivo texto contendo uma relação de operações bancárias (
  simplificadas)
  Dado um arquivo texto com uma relação de operações bancárias realizadas. Faça uma aplicação que leia esse arquivo,
  Agrupe as operações por conta bancária(id) e ordena por data da operação(dataHoraOperacao) de forma crescente. Após
  essas operações, a aplicação deverá executar as operações e calcular o saldo final.
</p>
<p>
  Por fim, deverá gerar um arquivo texto (um extrato) por conta bancária listando a relação de operações executadas e o saldo final.
</p>
<p>
  Obs.: se houver operação em duplicata, isto é, se todos os dados da operação forem idênticos inclusive a data/hora,
  o registro duplicado deverá ser descartado.
</p>
<p>
  Todas as contas iniciam zeradas. O saldo final pode ficar negativo.
</p>
<p>
  Segue o (pseudo) modelo:
</p>

<ul>
  OperacaoBancaria:
  <ul>
    <li>
      operador: nome de quem está realizando a operação
    </li>
    <li>
      tipo: tipo da operação (SAQUE/DEPOSITO)
    </li>
    <li>
      valor: valor da operação
    </li>
    <li>
      dataHoraOperacao: data da operação
    </li>
    <li>
      contaBancaria:
    </li>
    <ul>
      <li>
        id: identificador da conta bancaria
      </li>
      <li>
        banco: identificador numerico do banco
      </li>
      <li>
        agencia: identificador da agencia bancaria
      </li>
      <li>
        conta: numero da conta que será movimentada
      </li>
    </ul>
  </ul>
</ul>

<ul>
  <li>
    Requisitos obrigatórios:
  </li>
  <li>
    o arquivo contendo as operações bancárias será fornecido pelo professor (registros desordenados)
  </li>
  <li>
    aplicar princípios de OO
  </li>
  <li>
    utilizar uma ferramenta de build/gerenciador de pacotes: Maven ou Gradle
  </li>
  <li>
    utilizar ao menos 2 dependências
  </li>
  <li>
    utilizar ao menos 2 estruturas de dados diferentes
  </li>
</ul>

## Students
<ul>
    <li>
        Armando Moraes
    </li>
</ul>

## Technologies Used:

<table>
  <tr>
    <td>Java</td>
  </tr>

  <tr>
    <td>11</td>
  </tr>
</table>