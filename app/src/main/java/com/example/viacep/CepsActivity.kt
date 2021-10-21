package com.example.viacep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CepsActivity : AppCompatActivity() {

    lateinit var rvCeps: RecyclerView
    lateinit var adapterCeps: CepsAdapter

    lateinit var editTextUf: EditText
    lateinit var editTextCidade: EditText
    lateinit var editTextPalavraChave: EditText
    lateinit var buttonCepsBuscar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ceps)

        // Inicializar os componentes
        rvCeps = findViewById(R.id.rv_ceps)
        adapterCeps = CepsAdapter(this)

        editTextCidade = findViewById(R.id.editTextCidade)
        editTextUf = findViewById(R.id.editTextUF)
        editTextPalavraChave = findViewById(R.id.editTextPalavraChave)

        buttonCepsBuscar = findViewById(R.id.buttonCepsBuscar)

        // Determinar o layout da RecyclerView
        rvCeps.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // Definir o adapter da RecyclerView
        rvCeps.adapter = adapterCeps

        // Clique do botão buscar
        buttonCepsBuscar.setOnClickListener {
            listarCeps()
        }

    }

    fun listarCeps() {
        var listaCeps: List<CepModel> = listOf<CepModel>()

        val retrofit = RetrofitFactory.getRetrofit().create(CEPService::class.java)

        val call = retrofit.getCeps(
            editTextUf.text.toString(),
            editTextCidade.text.toString(),
            editTextPalavraChave.text.toString())


        // Efetua a chamada para o endpoint da API
        call.enqueue(object : Callback<List<CepModel>>{

            override fun onResponse(call: Call<List<CepModel>>, response: Response<List<CepModel>>) {
                listaCeps = response.body()!!
                adapterCeps.updateListaCeps(listaCeps)
            }

            override fun onFailure(call: Call<List<CepModel>>, t: Throwable) {
                Log.e("xptoholder", t.message.toString())
            }

        })

    }
}