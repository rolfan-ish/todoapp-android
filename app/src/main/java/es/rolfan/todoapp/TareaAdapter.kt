package es.rolfan.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TareaAdapter(private val tareas: MutableList<String>) : RecyclerView.Adapter<TareaAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.texto)
        val hecho: Button = view.findViewById(R.id.hecho)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tarea_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = tareas[position]
        holder.hecho.setOnClickListener {
            tareas.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
        }
    }

    override fun getItemCount() = tareas.size

    fun add(txt: String) {
        tareas.add(0, txt)
        notifyItemInserted(0)
    }
}