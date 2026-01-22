package com.example.viikkoteht1.domain

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import kotlin.collections.listOf
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {

    var allTasks = listOf<Task>()
    var tasks by mutableStateOf(listOf<Task>())
        private set
    init {
        allTasks = mockTasks
        tasks = allTasks
    }
    fun addTask(task: Task){
        allTasks = addTask(allTasks, task)
        tasks = allTasks
    }
    fun toggleDone(id: Int) {
        allTasks = toggleDone(allTasks,id)
        tasks = allTasks
    }
    fun removeTask(id: Int) {
        allTasks = allTasks.filterNot { it.id == id}
        tasks = allTasks
    }
    fun filterByDone(done: Boolean) {
        tasks = filterByDone(allTasks, done)
    }
    fun sortByDueDate() {
        tasks = sortByDueDate(tasks)
    }
    fun showAll() {
        tasks = allTasks
    }
}