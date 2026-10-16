package com.indianathe3rd.identity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
        enableEdgeToEdge()

        supabaseClient.handleDeeplinks(intent)

        setContent {
            IdentityTheme {
                val navController = rememberNavController()
                val currentBackStack by navController.currentBackStackEntryAsState()
                val currentDestination = currentBackStack?.destination
                NavHost(
                    navController = navController,
                    startDestination = AuthenticationDestination.route,
                    modifier = Modifier.fillMaxSize()
                ) {
                    composable(RootDestination.route) {
                        AppNavigation()
                    }

                    composable(AuthenticationDestination.route) {
                        AuthScreen(
                            onLoginScreen = {
                                navController.navigate(RootDestination.route) {
                                    popUpTo(AuthenticationDestination.route) {
                                        inclusive = true
                                    }
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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)){
            view,insets ->
            val bottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            insets
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        supabaseClient.handleDeeplinks(intent)
    }
}

