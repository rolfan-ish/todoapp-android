package es.rolfan.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Adaptador de tareas para un recycler view
 */
class TareaAdapter(val tareas: LinkedHashSet<String>) : RecyclerView.Adapter<TareaAdapter.ViewHolder>() {
    /**
     * Contendor de la vista de tareas
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.texto)
        val hecho: ImageView = view.findViewById(R.id.hecho)
    }

    /**
     * Inicializador de un contenedor de tarea
     * @param parent El contenedor donde se añadera el elemento
     * @param viewType
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tarea_item, parent, false)
        return ViewHolder(view)
    }

    /**
     * Añade una vista en el recyclerview
     * @param holder elemento donde se metara la vista
     * @param position Posición donde se deseea asignar la vista
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = tareas.elementAt(tareas.size - position - 1)
        holder.hecho.setOnClickListener {
            tareas.remove(holder.textView.text)
            notifyItemRemoved(holder.adapterPosition)
        }
    }

    /**
     * @return La cantdad de tareas en el contenedor
     */
    override fun getItemCount() = tareas.size

    /**
     * @param txt El texto del elemento ha añadir
     * @return true si se ha insertado el elemento, false sino
     */
    fun add(txt: String): Boolean {
        val added = tareas.add(txt)
        if (added) notifyItemInserted(0)
        return added
    }
}