package es.rolfan.todoapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var prefs: Preferencias
    lateinit var listaTareas: RecyclerView
    lateinit var tarea: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Inicializar
        prefs = Preferencias(this)
        tarea = findViewById(R.id.tarea)
        val adapter = TareaAdapter(prefs.read())
        listaTareas = findViewById(R.id.listaTareas)
        listaTareas.layoutManager = LinearLayoutManager(this)
        listaTareas.adapter = adapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onPause() {
        super.onPause()
        val adapter = listaTareas.adapter as TareaAdapter
        prefs.write(adapter.tareas)
    }

    fun agregarTarea(view: View) {
        val adapter = listaTareas.adapter as TareaAdapter
        if (!adapter.add(tarea.text.toString()))
            Toast.makeText(this, "El elemento ya existe", Toast.LENGTH_SHORT)
                .show()
    }
}