package es.rolfan.todoapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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

        // Inicializar
        val tarea: EditText = findViewById(R.id.tarea)
        val adapter = TareaAdapter(Preferencias.read(this))
        val listaTareas: RecyclerView = findViewById(R.id.listaTareas)
        listaTareas.layoutManager = LinearLayoutManager(this)
        listaTareas.adapter = adapter

        // Boton agregar
        findViewById<Button>(R.id.agregar).setOnClickListener {
            if (!adapter.add(tarea.text.toString()))
                Toast.makeText(this, "El elemento ya existe", Toast.LENGTH_SHORT)
                    .show()
            tarea.text.clear()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onPause() {
        super.onPause()
        val listaTareas: RecyclerView = findViewById(R.id.listaTareas)
        val adapter = listaTareas.adapter as TareaAdapter
        Preferencias.write(this, adapter.tareas)
    }
}