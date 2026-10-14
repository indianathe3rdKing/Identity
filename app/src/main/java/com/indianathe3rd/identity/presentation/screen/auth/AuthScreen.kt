package com.indianathe3rd.identity.presentation.screen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.indianathe3rd.identity.R
import com.indianathe3rd.identity.domain.model.Result
import com.indianathe3rd.identity.presentation.components.auth.GoogleSignIn
import com.indianathe3rd.identity.presentation.components.auth.Gradient
import com.indianathe3rd.identity.presentation.components.auth.RegisterHeader
import com.indianathe3rd.identity.presentation.ui.theme.Black
import com.indianathe3rd.identity.presentation.ui.theme.TextSecondary
import com.indianathe3rd.identity.presentation.ui.theme.White
import com.indianathe3rd.identity.presentation.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeApi::class)
@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel(),
    onLoginScreen: () -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val authState by viewModel.authState.collectAsState()
    val localSoftwareKeyboardController = LocalSoftwareKeyboardController.current
    LaunchedEffect(authState) {
        when (authState) {
            is Result.Success -> {
                onLoginScreen()
            }
            is Result.Loading->{

            }
            is Result.Idle -> Unit
            else -> {
                Result.Error("Something went wrong")
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black),
        contentAlignment = Alignment.TopCenter
    ){
        Gradient()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(top = 110.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RegisterHeader()
            Spacer(modifier = Modifier.height(35.dp))
            GoogleSignIn({
                localSoftwareKeyboardController?.hide()
                viewModel.onSignInWithGoogle()
            })
        }
    }
//    Scaffold(
//        snackbarHost = { SnackbarHost(snackBarHostState) },
//        topBar = {
//            TopAppBar(
//                navigationIcon = {
//                    IconButton(onClick = {
//                        navController.navigateUp()
//                    }) {
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBackIos,
//                            contentDescription = null,
//                            tint = MaterialTheme.colorScheme.onPrimary
//                        )
//                    }
//
//                },
////                backgroundColor = MaterialTheme.colorScheme.primary,
//                title = {
//                    Text(
//                        text = "Login",
//                        color = MaterialTheme.colorScheme.onPrimary
//                    )
//                }
//            )
//        }
//    ) { paddingValues ->
//        Column(
//            modifier = modifier
//                .padding(paddingValues)
//                .padding(20.dp)
//        ) {
//            val email = viewModel.email.collectAsState("")
//            val password = viewModel.password.collectAsState("")
//            OutlinedTextField(
//                label = {
//                    Text(
//                        text = "Email",
//                        color = MaterialTheme.colorScheme.primary,
//                        style = MaterialTheme.typography.titleMedium
//                    )
//                },
//                maxLines = 1,
//                shape = RoundedCornerShape(32),
//                modifier = modifier.fillMaxWidth(),
//                value = email.value,
//                onValueChange = {
//                    viewModel.onEmailChange(it)
//                }
//            )
//            OutlinedTextField(
//                label = {
//                    Text(
//                        text = "Password",
//                        color = MaterialTheme.colorScheme.primary,
//                        style = MaterialTheme.typography.titleMedium
//                    )
//                },
//                maxLines = 1,
//                shape = RoundedCornerShape(32),
//                modifier = modifier
//                    .fillMaxWidth()
//                    .padding(top = 12.dp),
//                value = password.value,
//                onValueChange = {
//                    viewModel.onPasswordChange(it)
//                }
//            )
//            val localSoftwareKeyboardController =
//                LocalSoftwareKeyboardController.current
//            Button(
//                modifier = modifier
//                    .fillMaxWidth()
//                    .padding(top = 12.dp),
//                onClick = {
//                    localSoftwareKeyboardController?.hide()
//                    viewModel.onSignInWithGoogle()
//                }
//            ) {
//                Text("Sign in with Google")
//            }
//            Button(
//                modifier = modifier
//                    .fillMaxWidth()
//                    .padding(top = 12.dp),
//                onClick = {
//                    localSoftwareKeyboardController?.hide()
//                    viewModel.onSignIn()
//                    coroutineScope.launch {
//                        snackBarHostState.showSnackbar(
//                            message = "Sign in successfully !",
//                            duration = SnackbarDuration.Long
//                        )
//                    }
//                }
//            ) {
//                Text("Sign in")
//            }
//            OutlinedButton(
//                modifier = modifier
//                    .fillMaxWidth()
//                    .padding(top = 12.dp),
//                onClick = {
//                    navController.navigate(SignUpDestination.route)
//                }
//            ) {
//                Text("Sign up,not real")
//            }
//        }
//    }
}