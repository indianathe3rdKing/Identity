package com.indianathe3rd.identity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.indianathe3rd.identity.presentation.navigation.AppNavigation
import com.indianathe3rd.identity.presentation.navigation.AuthenticationDestination
import com.indianathe3rd.identity.presentation.navigation.RootDestination
import com.indianathe3rd.identity.presentation.navigation.SignUpDestination
import com.indianathe3rd.identity.presentation.screen.auth.AuthScreen
import com.indianathe3rd.identity.presentation.screen.auth.SignUpScreen
import com.indianathe3rd.identity.presentation.ui.theme.IdentityTheme
import dagger.hilt.android.AndroidEntryPoint
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.handleDeeplinks
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var supabaseClient: SupabaseClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supabaseClient.handleDeeplinks(intent)

        enableEdgeToEdge()
        setContent {
            IdentityTheme {
                val navController = rememberNavController()
                val currentBackStack by navController.currentBackStackEntryAsState()
                val currentDestination = currentBackStack?.destination
                Scaffold { innerPadding ->
                    NavHost(
                        navController,
                        startDestination = AuthenticationDestination.route,
                        Modifier.padding(innerPadding)
                    ) {
                        composable(RootDestination.route) {
                            AppNavigation()
                        }

                        composable(AuthenticationDestination.route) {
                            AuthScreen(
                                navController = navController,
                                onLoginScreen = {
                                    navController.navigate(RootDestination.route) {
                                        popUpTo(AuthenticationDestination.route) { inclusive = true }
                                    }
                                }
                            )
                        }

                        composable(SignUpDestination.route) {
                            SignUpScreen(
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        supabaseClient.handleDeeplinks(intent)
    }
}

