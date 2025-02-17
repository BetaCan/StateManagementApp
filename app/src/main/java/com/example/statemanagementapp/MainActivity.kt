package com.example.statemanagementapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.statemanagementapp.ui.theme.StateManagementAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            StateManagementAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CounterAppWithDerivedState(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CounterAppWithDerivedState(modifier: Modifier = Modifier) {
    var counter by remember { mutableStateOf(0) }
    var threshold by remember { mutableStateOf(5) }
    val maxCounter = 10  // Maximum counter value
    val minCounter = 0   // Minimum counter value

    // Derived state for dynamic message
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
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        // Display counter with dynamic color
        Text(
            text = "Counter: $counter",
            style = MaterialTheme.typography.headlineMedium,
            color = if (counter >= threshold) Color.Green else Color.Red, // Dynamic color
            modifier = Modifier.padding(16.dp)
        )

        // Display derived state message
        Text(
            text = counterMessage,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(16.dp)
        )

        // Increment and Decrement buttons with range limiting
        Row {
            Button(
                onClick = { counter-- },
                enabled = counter > minCounter, // Disable if at minimum
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Decrement")
            }
            Button(
                onClick = { counter++ },
                enabled = counter < maxCounter, // Disable if at maximum
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Increment")
            }
        }

        // Reset button to clear state
        Button(
            onClick = {
                counter = 0
                threshold = 5
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Reset")
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
