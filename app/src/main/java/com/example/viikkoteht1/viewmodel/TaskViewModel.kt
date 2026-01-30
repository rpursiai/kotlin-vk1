package com.example.viikkoteht1.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.viikkoteht1.model.Task
import com.example.viikkoteht1.model.mockTasks
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.MutableStateFlow

class TaskViewModel : ViewModel() {

    private var allTasks: List<Task> = mockTasks

    private val _tasks = MutableStateFlow(allTasks)
    val tasks: StateFlow<List<Task>> = _tasks

    fun addTask(task: Task) {
        allTasks = allTasks + task
        _tasks.value = allTasks
    }

    fun toggleDone(id: Int) {
        allTasks = allTasks.map { t -> if (t.id == id) t.copy(done = !t.done) else t }
        _tasks.value = allTasks
    }

    fun removeTask(id: Int) {
        allTasks = allTasks.filterNot { it.id == id }
        _tasks.value = allTasks
    }

    fun updateTask(task: Task) {
        allTasks = allTasks.map { t -> if (t.id == task.id) task else t }
        _tasks.value = allTasks
    }

    fun filterByDone(done: Boolean) {
        _tasks.value = allTasks.filter { it.done == done }
    }

    fun sortByDueDate() {
        _tasks.value = _tasks.value.sortedBy { it.dueDate }
    }

    fun showAll() {
        _tasks.value = allTasks
    }
}