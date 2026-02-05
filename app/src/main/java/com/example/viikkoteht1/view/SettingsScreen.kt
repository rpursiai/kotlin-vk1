package com.example.viikkoteht1.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(
    darkTheme: Boolean,
    onToggleTheme: (Boolean) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Settings")
        Spacer(Modifier.height(12.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Text("Dark theme")
            Spacer(Modifier.weight(1f))
            Switch(
                checked = darkTheme,
                onCheckedChange = { onToggleTheme(it) }
            )
        }
    }
}