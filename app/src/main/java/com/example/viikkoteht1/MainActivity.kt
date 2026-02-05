package com.example.viikkoteht1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.viikkoteht1.ui.theme.Viikkoteht1Theme
import com.example.viikkoteht1.view.*
import com.example.viikkoteht1.viewmodel.TaskViewModel
import androidx.compose.foundation.layout.statusBarsPadding


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            var darkTheme by remember { mutableStateOf(false) }

            Viikkoteht1Theme(darkTheme = darkTheme) {
                val navController = rememberNavController()
                val vm: TaskViewModel = viewModel()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        Row(modifier = Modifier.statusBarsPadding().padding(horizontal = 16.dp)) {
                            Button(onClick = { navController.navigate(ROUTE_HOME) }) { androidx.compose.material3.Text("Home") }
                            Button(onClick = { navController.navigate(ROUTE_CALENDAR) }) { androidx.compose.material3.Text("Calendar") }
                            Button(onClick = { navController.navigate(ROUTE_SETTINGS) }) { androidx.compose.material3.Text("Settings") }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = ROUTE_HOME,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(ROUTE_HOME) {
                            HomeScreen(vm = vm)
                        }
                        composable(ROUTE_CALENDAR) {
                            CalendarScreen(vm = vm)
                        }
                        composable(ROUTE_SETTINGS) {
                            SettingsScreen(
                                darkTheme = darkTheme,
                                onToggleTheme = { darkTheme = it }
                            )
                        }
                    }
                }
            }
        }
    }
}
