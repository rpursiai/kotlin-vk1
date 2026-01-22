package com.example.viikkoteht1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikkoteht1.domain.Task
import com.example.viikkoteht1.domain.TaskViewModel
import com.example.viikkoteht1.domain.addTask
import com.example.viikkoteht1.domain.filterByDone
import com.example.viikkoteht1.domain.mockTasks
import com.example.viikkoteht1.domain.sortByDueDate
import com.example.viikkoteht1.domain.toggleDone
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.foundation.lazy.items


@Composable
fun Homescreen(vm: TaskViewModel = viewModel()) {
    val shownTasks = vm.tasks
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var priorityText by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }

    Column {
        Text("Home")

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") }
        )
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") }
        )
        OutlinedTextField(
            value = priorityText,
            onValueChange = { priorityText = it },
            label = { Text("Priority") }
        )
        OutlinedTextField(
            value = dueDate,
            onValueChange = { dueDate = it },
            label = { Text("Due date (yyyy-dd-MM)") }
        )

        Spacer(Modifier.height(12.dp))

        Row {
            Button(
                onClick = {
                    val newId = (vm.tasks.maxOfOrNull { it.id } ?: 0) + 1
                    val newTask = Task(
                        id = newId,
                        title = if (title.isBlank()) "Untitled" else title,
                        description = if (description.isBlank()) "-" else description,
                        priority = priorityText.toIntOrNull() ?: 1,
                        dueDate = if (dueDate.isBlank()) "1970-01-01" else dueDate,
                        done = false
                    )
                    vm.addTask(newTask)

                    // clear input fields
                    title = ""
                    description = ""
                    priorityText = ""
                    dueDate = ""
                }
            ) { Text("addTask") }

            Spacer(Modifier.width(8.dp))

            Button(
                onClick = {
                    vm.sortByDueDate()
                }
            ) { Text("sortByDueDate") }
        }

        Spacer(Modifier.height(8.dp))

        Row {
            Button(onClick = {vm.showAll()}) { Text("Show All") }
            Spacer(Modifier.width(8.dp))
            Button(onClick = { vm.filterByDone(true) }) { Text("Show Done") }
            Spacer(Modifier.width(8.dp))
            Button(onClick = { vm.filterByDone(false) }) { Text("Show Not done") }
        }

        Spacer(Modifier.height(12.dp))


        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(shownTasks, key = { it.id }) { task ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
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
}