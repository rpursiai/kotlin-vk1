package com.example.viikkoteht1.model

fun addTask(list: List<Task>, newTask: Task): List<Task> {
    return list + newTask
}

fun sortTasksByPriority(list: List<Task>): List<Task> {
    return list.sortedBy { it.priority }
}

fun toggleDone(list: List<Task>, id: Int): List<Task> {
    return list.map { task ->
        if (task.id == id) task.copy(done = !task.done) else task
    }
}

fun filterByDone(list: List<Task>, done: Boolean): List<Task> {
    return list.filter { it.done == done }
}

fun sortByDueDate(list: List<Task>): List<Task> {
    return list.sortedBy { it.dueDate }
}