package com.indianathe3rd.identity.presentation.screen.auth

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.indianathe3rd.identity.domain.model.Result
import com.indianathe3rd.identity.presentation.components.auth.AuthButton
import com.indianathe3rd.identity.presentation.components.auth.EmailField
import com.indianathe3rd.identity.presentation.components.auth.ForgotButton
import com.indianathe3rd.identity.presentation.components.auth.GoogleSignIn
import com.indianathe3rd.identity.presentation.components.auth.Gradient
import com.indianathe3rd.identity.presentation.components.auth.PasswordField
import com.indianathe3rd.identity.presentation.components.auth.RegisterHeader
import com.indianathe3rd.identity.presentation.navigation.SendMail
import com.indianathe3rd.identity.presentation.ui.theme.Black
import com.indianathe3rd.identity.presentation.ui.theme.DarkPurple
import com.indianathe3rd.identity.presentation.ui.theme.White
import com.indianathe3rd.identity.presentation.viewmodel.AuthViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private val TAG = "AuthScreen"

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeApi::class)
@Composable
fun AuthScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel(),
    onLoginScreen: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val authState by viewModel.authState.collectAsState()
    val localSoftwareKeyboardController = LocalSoftwareKeyboardController.current
    var isSignIn = remember { mutableStateOf(false) }
    val errorMessage = (authState as? Result.Error)?.message

    LaunchedEffect(authState) {
        when (authState) {
            is Result.Success -> {
                onLoginScreen()
            }

            is Result.Loading -> {
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
    ) {
        Gradient()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)

                .imePadding()
                .navigationBarsPadding()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(110.dp))
            RegisterHeader(isSignIn)
            Spacer(modifier = Modifier.height(35.dp))
            GoogleSignIn({
                localSoftwareKeyboardController?.hide()
                viewModel.onSignInWithGoogle()
            })

            Row(
                modifier = Modifier
                    .padding(vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(White.copy(0.2f))
                )
                Text(
                    text = "Or",
                    color = White.copy(0.7f),
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(White.copy(0.2f))
                )
            }

            EmailField(viewModel)
            Spacer(modifier = Modifier.height(20.dp))

            PasswordField(
                viewModel = viewModel,
                onFocused = {
                    coroutineScope.launch {
                        delay(300)
                        bringIntoViewRequester.bringIntoView()
                    }
                }
            )
            if (errorMessage != null) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(0.9f)
                        ,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(
                modifier = if (errorMessage != null) Modifier.height(25.dp)
                else Modifier.height(
                    35.dp
                )
            )
            AuthButton(isSignIn,viewModel)
            Spacer(modifier = Modifier.height(5.dp))
            TextButton(
                onClick = {
                    navController.navigate(SendMail.route)
                }
            ) {
                Text(text = "Forgot Password?",
                    fontWeight = FontWeight.Light,
                    color = DarkPurple
                )
            }
            ForgotButton(isSignIn)

        }
    }

}