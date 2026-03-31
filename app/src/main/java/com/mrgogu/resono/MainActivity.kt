package com.mrgogu.resono

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mrgogu.resono.ui.navigation.NavGraph
import dagger.hilt.android.AndroidEntryPoint

// Required for Hilt to inject dependencies into this screen
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavGraph()
        }
    }
}


@Preview(showBackground = false)
@Composable
fun GreetingPreview() {
    NavGraph()
}