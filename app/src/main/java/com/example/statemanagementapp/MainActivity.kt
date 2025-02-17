package com.example.statemanagementapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge // Import the enableEdgeToEdge function
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.statemanagementapp.ui.theme.StateManagementAppTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enable edge-to-edge content
        enableEdgeToEdge()
        setContent {
            StateManagementAppTheme {
                // Scaffold layout to structure the app layout
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CounterApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
// Composable for a simple counter app
@Composable
fun CounterApp(modifier: Modifier = Modifier) {
    var counter by remember { mutableStateOf(0) } // State for the counter
    Column(
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        // Display the current counter value
        Text(
            text = "Counter: $counter",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )
        // Button to increment the counter
        Button(onClick = { counter++ }, modifier = Modifier.padding(8.dp)) {
            Text("Increment")
        }
        // Button to decrement the counter
        Button(onClick = { counter-- }, modifier = Modifier.padding(8.dp)) {
            Text("Decrement")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StateManagementAppTheme {
        CounterApp()
    }
}