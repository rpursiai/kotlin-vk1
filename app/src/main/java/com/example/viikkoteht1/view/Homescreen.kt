package com.example.viikkoteht1.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikkoteht1.model.Task
import com.example.viikkoteht1.viewmodel.TaskViewModel

@Composable
fun HomeScreen(vm: TaskViewModel = viewModel()) {

    val tasks by vm.tasks.collectAsState()

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var priorityText by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }

    var selectedTask by remember { mutableStateOf<Task?>(null) }



    Column(modifier = Modifier.padding(16.dp)) {
        Text("Home")

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
        OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Description") })
        OutlinedTextField(value = priorityText, onValueChange = { priorityText = it }, label = { Text("Priority") })
        OutlinedTextField(value = dueDate, onValueChange = { dueDate = it }, label = { Text("Due date") })

        Spacer(Modifier.height(12.dp))

        Button(onClick = {
            val newId = (tasks.maxOfOrNull { it.id } ?: 0) + 1
            vm.addTask(
                Task(
                    id = newId,
                    title = if (title.isBlank()) "Untitled" else title,
                    description = if (description.isBlank()) "-" else description,
                    priority = priorityText.toIntOrNull() ?: 1,
                    dueDate = if (dueDate.isBlank()) "01-01-1970" else dueDate,
                    done = false
                )
            )

            title = ""
            description = ""
            priorityText = ""
            dueDate = ""
        }) {
            Text("addTask")
        }

        Spacer(Modifier.height(12.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(tasks, key = { it.id }) { task ->
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
                    Text("${task.id}. ${task.title} (${task.dueDate})")
                    Spacer(Modifier.weight(1f))
                    Button(onClick = { vm.removeTask(task.id) }) {
                        Text("Delete")
                    }
                }
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
