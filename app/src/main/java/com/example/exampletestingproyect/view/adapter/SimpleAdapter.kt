package com.example.exampletestingproyect.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exampletestingproyect.R
import java.util.Locale

class SimpleAdapter(
    private val name: String,
    private var list: ArrayList<String>,
    private val listener: Adapter.ListenerAdapter
) :
    RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: String): View {
            val textView = view.findViewById(R.id.textView) as TextView
            textView.text = " - "
            textView.append(
                item.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() })
            return view
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.simple_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position]).setOnClickListener {
            listener.clickSubList(name, list[position])
        }
    }

    override fun getItemCount(): Int = list.size
}