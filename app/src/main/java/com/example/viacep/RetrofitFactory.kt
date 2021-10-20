package com.example.viacep

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/* *
*  **** Prof. Celso Furtado - SENAI Jandir ****
*  ************************************************************************************
*  Esta Classe é utilizada para criar o cliente que fará a requisição para a API
*  Aqui, fazemos uma verificação se a instância retrofit já está inicializada
*  Se não estiver, criamos o cliente, senão retornamos a instância já criada
*  Chamamos isso de "Singleton", garantimos apenas uma instância de um objeto
*  ************************************************************************************
* */

class RetrofitFactory {

    companion object {

        private lateinit var retrofit: Retrofit
        private val baseUrl = "https://viacep.com.br/ws/"

        fun getRetrofit() : Retrofit {

            if (!::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(OkHttpClient.Builder().build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit
        }

    }

}