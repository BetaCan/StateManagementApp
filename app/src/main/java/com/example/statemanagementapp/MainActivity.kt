
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
                    CounterAppWithDerivedState(modifier =
                    Modifier.padding(innerPadding))
                }
            }
        }
    }
}
// Composable for counter app with derived state for dynamic message
@Composable
fun CounterAppWithDerivedState(modifier: Modifier = Modifier) {
    var counter by remember { mutableStateOf(0) } // Counter state
    var threshold by remember { mutableStateOf(5) } // User-defined threshold
// Derived state for dynamic message based on counter and threshold
    val counterMessage by remember {
        derivedStateOf {
            if (counter >= threshold) {
                "Counter is above or equal to the threshold."
            } else {
                "Counter is below the threshold."
            }
        }
    }
    Column(
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
// Display counter and message
        Text(
            text = "Counter: $counter",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = counterMessage, // Derived state message
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp)
        )
// Increment and Decrement buttons
        Button(onClick = { counter++ }, modifier = Modifier.padding(8.dp)) {
            Text("Increment")
        }
        Button(onClick = { counter-- }, modifier = Modifier.padding(8.dp)) {
            Text("Decrement")
        }
// Slider to adjust the threshold
        Spacer(modifier = Modifier.height(16.dp))
        Text("Adjust Threshold: $threshold")
        Slider(
            value = threshold.toFloat(),
            onValueChange = { threshold = it.toInt() },
            valueRange = 1f..10f,
            modifier = Modifier.padding(16.dp)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StateManagementAppTheme {
        CounterAppWithDerivedState()
    }
}

