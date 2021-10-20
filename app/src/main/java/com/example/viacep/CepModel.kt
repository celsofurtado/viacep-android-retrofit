package com.example.viacep

/* *
*  **** Prof. Celso Furtado - SENAI Jandir ****
*  *********************************************************************************************
*  Esta classe é utilizada para definir o formato do objeto cep
*  Observem que os atributos são as propriedades do json de resposta da API
*  Isso é importante para que o GSon possa converter a resposta da API em um Objeto no Kotlin
*  *********************************************************************************************
* */

data class CepModel(
    var cep: String,
    var logradouro: String,
    var complemento: String,
    var bairro: String,
    var localidade: String,
    var uf: String,
    var ibge: String,
    var gia: String,
    var ddd: String,
    var siafi: String
)