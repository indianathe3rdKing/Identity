package com.indianathe3rd.identity.presentation.screen.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.indianathe3rd.identity.domain.model.Result
import com.indianathe3rd.identity.presentation.components.auth.EmailField
import com.indianathe3rd.identity.presentation.components.auth.Gradient
import com.indianathe3rd.identity.presentation.components.auth.PasswordField
import com.indianathe3rd.identity.presentation.components.auth.ResetPassword
import com.indianathe3rd.identity.presentation.ui.theme.Black
import com.indianathe3rd.identity.presentation.ui.theme.TextPrimary
import com.indianathe3rd.identity.presentation.ui.theme.White
import com.indianathe3rd.identity.presentation.viewmodel.AuthViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun EmailScreen(viewModel: AuthViewModel = hiltViewModel(), navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val authState by viewModel.authState.collectAsState()
    val errorMessage = (authState as? Result.Error)?.message

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
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(110.dp))
            ResetPassword("Enter your Email", "Enter your Account Email")
            Spacer(Modifier.height(25.dp))
            EmailField(viewModel)

            if (errorMessage != null) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 16.dp),
                    textAlign = TextAlign.Center
                )
            } else if (authState is Result.Success) {
                Text(
                    text = "Password reset email sent! Please check your inbox.",
                    color = White,
                    modifier = Modifier.padding(top = 16.dp),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(Modifier.height(35.dp))
            Button(
                onClick = {
                    viewModel.onSendPasswordReset()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = White
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .bringIntoViewRequester(bringIntoViewRequester)
            ) {
                Text(
                    text = "Send",
                    modifier = Modifier.padding(vertical = 4.dp),
                    color = TextPrimary
                )
            }
        }
    }
}