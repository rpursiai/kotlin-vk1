package com.example.viikkoteht1.view

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.example.viikkoteht1.model.Task

@Composable
fun DetailScreen(
    task: Task,
    onDismiss: () -> Unit,
    onSave: (Task) -> Unit,
    onDelete: (Int) -> Unit
) {
    var title by remember { mutableStateOf(task.title) }
    var description by remember { mutableStateOf(task.description) }
    var priorityText by remember { mutableStateOf(task.priority.toString()) }
    var dueDate by remember { mutableStateOf(task.dueDate) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Task details") },
        text = {
            androidx.compose.foundation.layout.Column {
                OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
                OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Description") })
                OutlinedTextField(value = priorityText, onValueChange = { priorityText = it }, label = { Text("Priority") })
                OutlinedTextField(value = dueDate, onValueChange = { dueDate = it }, label = { Text("Due date") })
            }
        },
        confirmButton = {
            Button(onClick = {
                onSave(
                    task.copy(
                        title = title,
                        description = description,
                        priority = priorityText.toIntOrNull() ?: task.priority,
                        dueDate = dueDate
                    )
                )
                onDismiss()
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = {
                onDelete(task.id)
                onDismiss()
            }) {
                Text("Delete")
            }
        }
    )
}
