package es.rolfan.todoapp

import android.app.Activity
import android.content.Context

private const val TASKS_KEY = "tasks"

/**
 * Clase de utilidad para leer preferencias
 */
class Preferencias {
    companion object {
        /**
         * @param ctx La actividad de la que se quieren sacar preferencias
         * @return set de tareas hechas
         */
        fun read(ctx: Activity) =
            LinkedHashSet(ctx.getPreferences(Context.MODE_PRIVATE)
                .getStringSet(TASKS_KEY, setOf()))

        /**
         * @param ctx La actividad donde se quieren guardar preferencias
         * @param tasks Las preferencias que se quieren guardar
         */
        fun write(ctx: Activity, tasks: LinkedHashSet<String>) {
            with (ctx.getPreferences(Context.MODE_PRIVATE).edit()) {
                putStringSet(TASKS_KEY, tasks)
                apply()
            }
        }
    }
}