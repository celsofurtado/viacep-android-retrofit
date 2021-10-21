package com.example.viacep

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/* *
*  **** Prof. Celso Furtado - SENAI Jandir ****
*  ************************************************************************************
*  Esta interface é utilizada para criar as chamadas HTTP para os endpoints da API
*  ************************************************************************************
* */

interface CEPService {

    @GET("{cep}/json")
    fun getCep(@Path("cep") cep: String) : Call<CepModel>

    @GET("{uf}/{cidade}/{palavraChave}/json")
    fun getCeps(
        @Path("uf") uf: String,
        @Path("cidade") cidade: String,
        @Path("palavraChave") palavraChave: String) : Call<List<CepModel>>

}