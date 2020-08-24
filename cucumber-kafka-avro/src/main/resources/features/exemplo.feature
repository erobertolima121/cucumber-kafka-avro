#language: pt

Funcionalidade: Exemplo

  @exemplo
  Cenario: Teste de Exemplo
    Dado que eu produzo uma mensagem
    Quando eu realizar o consumo dessa mensagem
    Então quero ver a idade de "34" na mensagem consumida

  @aninhamento
  Cenario: Consumir endereco da lista
    Dado que eu produzo uma mensagem
    Quando eu realizar o consumo dessa mensagem
    Então quero ver o pais "[Brasil]"