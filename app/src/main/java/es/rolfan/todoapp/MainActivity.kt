package es.rolfan.todoapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val adapter = TareaAdapter(mutableListOf())
        val rview = findViewById<RecyclerView>(R.id.listaTareas)
        rview.layoutManager = LinearLayoutManager(this)
        rview.adapter = adapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun agregarTarea(view: View) {
        val lst = findViewById<RecyclerView>(R.id.listaTareas)
        val txt = findViewById<TextView>(R.id.tarea).text.toString()
        val adapter = lst.adapter as TareaAdapter
        adapter.add(txt)
    }


}