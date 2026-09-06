package com.indianathe3rd.identity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.indianathe3rd.identity.presentation.navigation.AppNavigation
import com.indianathe3rd.identity.presentation.ui.theme.IdentityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IdentityTheme {

                AppNavigation()
            }
        }
    }
}

