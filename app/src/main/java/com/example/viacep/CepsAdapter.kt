package com.example.viacep

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CepsAdapter(val context: Context) : RecyclerView.Adapter<CepsAdapter.CepViewHolder>() {

    var listaCeps = emptyList<CepModel>()

    fun updateListaCeps(lista: List<CepModel>) {
        listaCeps = lista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CepViewHolder {

        Log.i("xptoholder", "onCreateViewHolder")

        val view = LayoutInflater.from(context).inflate(R.layout.lista_ceps_layout, parent, false)

        return CepViewHolder(view)

    }

    override fun onBindViewHolder(holder: CepViewHolder, position: Int) {
        Log.i("xptoholder", "onBindViewHolder")
        val endereco = listaCeps[position]
        holder.tvCep.text = endereco.cep
        holder.tvLogradouro.text = endereco.logradouro
        holder.tvBairro.text = endereco.bairro
    }

    override fun getItemCount(): Int {
        Log.i("xptoholder", "getItemCount")
        return listaCeps.size
    }

    // Classe que representa cada linha da RecyclerView
    class CepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvCep = itemView.findViewById<TextView>(R.id.tv_cep)
        val tvLogradouro = itemView.findViewById<TextView>(R.id.tv_logradouro)
        val tvBairro = itemView.findViewById<TextView>(R.id.tv_bairro)

    }

}