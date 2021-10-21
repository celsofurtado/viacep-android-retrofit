package com.example.viacep

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {

    lateinit var editTextCep: EditText
    lateinit var buttonBuscarCep: Button
    lateinit var textViewResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ocultar a toolBar/Appbar
        //supportActionBar!!.hide()

        editTextCep = findViewById(R.id.editTextCEP)
        textViewResultado = findViewById(R.id.textViewResultado)
        buttonBuscarCep = findViewById(R.id.buttonBuscarCEP)

        buttonBuscarCep.setOnClickListener {

            if (!editTextCep.text.isNullOrEmpty()) {
                buscarCep()
            } else {
                editTextCep.error = "CEP é obrigatório!"
            }

        }

    }

    private fun buscarCep() {

        val retrofit = RetrofitFactory.getRetrofit().create(CEPService::class.java)

        val call: Call<CepModel> = retrofit.getCep(editTextCep.text.toString())

        call.enqueue(object : Callback<CepModel> {

            override fun onResponse(call: Call<CepModel>, response: Response<CepModel>) {

                if (!response.message().equals("Bad Request")) {

                    val cep = response.body()

                    val resultado = StringBuilder()
                    resultado
                        .appendLine("Logradouro: ${cep!!.logradouro}")
                        .appendLine("Bairro: ${cep!!.bairro}")
                        .appendLine("Cidade: ${cep!!.localidade}")
                        .appendLine("UF: ${cep!!.uf}")
                        .appendLine("Complemento: ${cep!!.complemento}")
                        .appendLine("Cód. IBGE: ${cep!!.ibge}")

                    textViewResultado.text = resultado

                } else {
                    editTextCep.error = "Formato do CEP incorreto!"
                }

            }

            override fun onFailure(call: Call<CepModel>, t: Throwable) {
                Log.e("viacep", t.message.toString())
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val i = Intent(this, CepsActivity::class.java)
        startActivity(i)

        return true
    }

}