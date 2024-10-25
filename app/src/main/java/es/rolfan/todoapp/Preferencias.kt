package es.rolfan.todoapp

import android.app.Activity
import android.content.Context

class Preferencias(ctx: Activity) {
    companion object {
        const val TASKS_KEY = "tasks"
    }

    private val prefs = ctx.getPreferences(Context.MODE_PRIVATE);

    // Siguiendo el contrato, sabemos que este valor nunca sera null
    fun read() = prefs.getStringSet(TASKS_KEY, linkedSetOf())!! as LinkedHashSet<String>

    fun write(tasks: LinkedHashSet<String>) {
        // La palabra cable `with` es azucar, en este caso tiene el siguiente comportamiento:
        // ```
        // val editor = prefs.edit()
        // editor.putStringSet(TASKS_KEY, tasks)
        // editor.apply()
        // ```
        with (prefs.edit()) {
            putStringSet(TASKS_KEY, tasks)
            apply()
        }
    }
}