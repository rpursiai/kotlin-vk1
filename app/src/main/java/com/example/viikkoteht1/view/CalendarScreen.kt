package com.example.viikkoteht1.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viikkoteht1.model.Task
import com.example.viikkoteht1.viewmodel.TaskViewModel

@Composable
fun CalendarScreen(vm: TaskViewModel) {
    val tasks by vm.tasks.collectAsState()
    var selectedTask by remember { mutableStateOf<Task?>(null) }

    val grouped = tasks.groupBy { it.dueDate }.toSortedMap()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Calendar")

        Spacer(Modifier.height(12.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            grouped.forEach { (date, dayTasks) ->
                item {
                    Text(date)
                    Spacer(Modifier.height(6.dp))
                }

                items(dayTasks, key = { it.id }) { task ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable { selectedTask = task }
                    ) {
                        Checkbox(
                            checked = task.done,
                            onCheckedChange = { vm.toggleDone(task.id) }
                        )
                        Spacer(Modifier.width(8.dp))
                        Text("${task.id}. ${task.title}")
                    }
                }

                item { Spacer(Modifier.height(12.dp)) }
            }
        }
    }

    selectedTask?.let { task ->
        DetailScreen(
            task = task,
            onDismiss = { selectedTask = null },
            onSave = { vm.updateTask(it) },
            onDelete = { vm.removeTask(it) }
        )
    }
}