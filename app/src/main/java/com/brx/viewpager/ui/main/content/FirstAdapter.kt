package com.brx.viewpager.ui.main.content

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brx.viewpager.R
import kotlinx.android.synthetic.main.item.view.*

class FirstAdapter(private val dataset: List<Pessoa>) : RecyclerView.Adapter<FirstAdapter.PessoaHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PessoaHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)

        return PessoaHolder(item)
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: PessoaHolder, position: Int) {
        holder.bind(dataset[position].nome)
    }

    class PessoaHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(text: String) {
            itemView.titulo.text = text
        }
    }
}

data class Pessoa(val nome: String)