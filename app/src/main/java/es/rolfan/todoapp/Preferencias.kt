package es.rolfan.todoapp

import android.app.Activity
import android.content.Context

private const val TASKS_KEY = "tasks"

class Preferencias {
    companion object {
        fun read(ctx: Activity) =
            LinkedHashSet(ctx.getPreferences(Context.MODE_PRIVATE)
                .getStringSet(TASKS_KEY, setOf()))

        fun write(ctx: Activity, tasks: LinkedHashSet<String>) {
            with (ctx.getPreferences(Context.MODE_PRIVATE).edit()) {
                putStringSet(TASKS_KEY, tasks)
                apply()
            }
        }
    }
}