package com.mrgogu.resono

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mrgogu.resono.ui.auth.viewmodel.AuthGate
import com.mrgogu.resono.ui.theme.ResonoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ResonoTheme {
                AuthGate()
            }
        }
    }
}
