package com.example.exampletestingproyect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exampletestingproyect.adapter.Adapter
import com.example.exampletestingproyect.junit.GeneratePhrases

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView(){
        findViewById<Button>(R.id.button).setOnClickListener{
            click()
        }
        recyclerView = findViewById(R.id.recyclerView)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = Adapter( arrayListOf<String>())
        recyclerView.adapter = adapter
    }

    private fun click(){
        val listType = listOf(GeneratePhrases.TYPE1, GeneratePhrases.TYPE2, GeneratePhrases.TYPE3, "else")
        val itemString = GeneratePhrases().getPhrases(listType.random()) ?: "null"
        adapter.addItemInList(itemString)
    }
}