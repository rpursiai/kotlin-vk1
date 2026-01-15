package com.example.viikkoteht1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.viikkoteht1.ui.theme.Viikkoteht1Theme
import com.example.viikkoteht1.domain.mockTasks
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.setValue
import androidx.compose.material3.OutlinedTextField
import com.example.viikkoteht1.domain.Task
import com.example.viikkoteht1.domain.addTask
import com.example.viikkoteht1.domain.filterByDone
import com.example.viikkoteht1.domain.sortTasksByPriority
import com.example.viikkoteht1.domain.sortByDueDate
import com.example.viikkoteht1.domain.toggleDone
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val tag = "MainActivity"
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Viikkoteht1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
    override fun onStart() {
        val tag ="MainActivity"
        super.onStart()
        Log.d(tag, "onStart")
    }
    override fun onResume() {
        val tag = "MainActivity"
        super.onResume()
        Log.d(tag, "onResume")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(text = "Hello $name!")
        ParentComponent()
    }
}
@Composable
fun NameTextField(
    name: String,
    onNameChange: (String) -> Unit
) {
    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text("Nimi") }
    )
}


@Preview(showBackground = true)
@Composable
fun ParentComponent() {
    var allTasks by remember { mutableStateOf(mockTasks) }
    var shownTasks by remember { mutableStateOf(mockTasks) }

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
                    val newTask = Task(
                        id = allTasks.size + 1,
                        title = if (title.isBlank()) "Untitled" else title,
                        description = if (description.isBlank()) "-" else description,
                        priority = priorityText.toIntOrNull() ?: 1,
                        dueDate = if (dueDate.isBlank()) "1970-01-01" else dueDate,
                        done = false
                    )
                    allTasks = addTask(allTasks, newTask)
                    shownTasks = allTasks

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
                    allTasks = sortByDueDate(allTasks)
                    shownTasks = allTasks
                }
            ) { Text("sortByDueDate") }
        }

        Spacer(Modifier.height(8.dp))

        Row {
            Button(onClick = { shownTasks = allTasks }) { Text("Show All") }
            Spacer(Modifier.width(8.dp))
            Button(onClick = { shownTasks = filterByDone(allTasks, true) }) { Text("Show Done") }
            Spacer(Modifier.width(8.dp))
            Button(onClick = { shownTasks = filterByDone(allTasks, false) }) { Text("Show Not done") }
        }

        Spacer(Modifier.height(12.dp))

        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .weight(1f)
        ) {
            shownTasks.forEach { task ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text("${task.id}. ${task.title} (${task.dueDate}) done=${task.done}")
                    Spacer(Modifier.width(8.dp))
                    Button(onClick = {
                        allTasks = toggleDone(allTasks, task.id)
                        shownTasks = allTasks
                    }) {
                        Text("toggle done")
                    }
                }
            }
        }
    }
}