package es.rolfan.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TareaAdapter(val tareas: LinkedHashSet<String>) : RecyclerView.Adapter<TareaAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.texto)
        val hecho: ImageView = view.findViewById(R.id.hecho)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tarea_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = tareas.elementAt(tareas.size - position - 1)
        holder.hecho.setOnClickListener {
            tareas.remove(holder.textView.text)
            notifyItemRemoved(holder.adapterPosition)
        }
    }

    override fun getItemCount() = tareas.size

    fun add(txt: String): Boolean {
        val added = tareas.add(txt)
        if (added) notifyItemInserted(0)
        return added
    }
}