package com.indianathe3rd.identity.presentation.screen.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.indianathe3rd.identity.domain.model.Result
import com.indianathe3rd.identity.presentation.navigation.AuthenticationDestination
import com.indianathe3rd.identity.presentation.navigation.RootDestination
import com.indianathe3rd.identity.presentation.navigation.SignUpDestination
import com.indianathe3rd.identity.presentation.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val authState by viewModel.authState.collectAsState()

    LaunchedEffect(authState) {
        when (authState) {
            is Result.Success -> {
                navController.navigate(RootDestination.route) {
                    popUpTo(AuthenticationDestination.route) { inclusive = true }
                }
            }
            else -> {}
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIos,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
//                backgroundColor = MaterialTheme.colorScheme.primary,
                title = {
                    Text(
                        text = "Sign Up",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleMedium
                    )
                },
            )
        }


    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .padding(20.dp)
        ) {
            val email = viewModel.email.collectAsState("")
            val password = viewModel.password.collectAsState("")

            OutlinedTextField(
                label = {
                    Text(
                        text = "Email",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                maxLines = 1,
                shape = RoundedCornerShape(32),
                modifier = modifier
                    .fillMaxWidth(),
                value = email.value,
                onValueChange = {
                    viewModel.onEmailChange(it)
                },
            )
            OutlinedTextField(
               label = {
                   Text(
                       text = "Password",
                       color = MaterialTheme.colorScheme.primary,
                       style = MaterialTheme.typography.titleMedium
                   )
               } ,
                maxLines = 1,
                shape = RoundedCornerShape(32),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                value = password.value,
                onValueChange ={
                    viewModel.onPasswordChange(it)
                }
            )
            val localSoftwareKeyboardController = LocalSoftwareKeyboardController.current
            Button(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                onClick = {
                    localSoftwareKeyboardController?.hide()
                    viewModel.onSignInWithGoogle()
                }
            ) {
                Text("Sign in with Google")
            }
            Button(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top=12.dp),
                onClick = {
                    localSoftwareKeyboardController?.hide()
                    viewModel.onSignUp()
                    coroutineScope.launch {
                        snackBarHostState.showSnackbar(
                            message = "Sign up successfully !",
                            duration = SnackbarDuration.Long
                        )
                    }
                }
            ) {
                Text("Sign up")
            }
            OutlinedButton(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                onClick = {
                    navController.navigate(RootDestination.route)
                }
            ) {
                Text("Sign In,not real")
            }
        }
    }
}