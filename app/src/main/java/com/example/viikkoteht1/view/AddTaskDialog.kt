package com.example.viikkoteht1.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*

@Composable
fun AddTaskDialog(
    onDismiss: () -> Unit,
    onSave: (title: String, description: String, priorityText: String, dueDate: String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var priorityText by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add task") },
        text = {
            Column {
                OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
                OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Description") })
                OutlinedTextField(value = priorityText, onValueChange = { priorityText = it }, label = { Text("Priority") })
                OutlinedTextField(value = dueDate, onValueChange = { dueDate = it }, label = { Text("Due date") })
            }
        },
        confirmButton = {
            Button(onClick = {
                onSave(title, description, priorityText, dueDate)
                onDismiss()
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
