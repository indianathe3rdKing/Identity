package com.indianathe3rd.identity.presentation.screen.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.clerk.ui.auth.AuthMode
import com.clerk.ui.auth.AuthView
import com.indianathe3rd.identity.presentation.navigation.AppNavigation
import com.indianathe3rd.identity.presentation.viewmodel.AuthViewModel

@Composable
fun AuthScreen(viewModel: AuthViewModel = viewModel()) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        when(state){
            AuthViewModel.MainUiState.Loading -> CircularProgressIndicator()
            AuthViewModel.MainUiState.SignedOut -> AuthView(mode =AuthMode.SignUp)
            AuthViewModel.MainUiState.SignedIn -> AppNavigation()
        }
    }

}