package com.example.exampletestingproyect.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exampletestingproyect.R
import com.example.exampletestingproyect.models.BreedModel
import java.util.Locale

class Adapter(private var list: ArrayList<BreedModel>, private val listener: ListenerAdapter) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: BreedModel, listener: ListenerAdapter) {
            val textView = view.findViewById(R.id.textView) as TextView
            textView.text =
                item.name.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(Locale.getDefault())
                    else it.toString()
                }
            textView.setOnClickListener {
                listener.click(item.name)
            }
            if (item.listTypeBreed.isNotEmpty()) {
                val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
                recyclerView.layoutManager = LinearLayoutManager(view.context)
                recyclerView.adapter = SimpleAdapter(item.name, item.listTypeBreed, listener)
                recyclerView.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(listString: ArrayList<BreedModel>) {
        this.list = listString
        this.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateBreedList(nameBreed: String, listString: List<String>) {
        val position = list.indexOf(BreedModel(nameBreed))
        list[position] = BreedModel(nameBreed, listString as ArrayList<String>)
        this.notifyDataSetChanged()
    }

    interface ListenerAdapter {
        fun click(itemString: String)
        fun clickSubList(name: String, itemString: String)
    }
}