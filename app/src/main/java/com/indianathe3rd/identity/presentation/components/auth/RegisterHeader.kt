package com.indianathe3rd.identity.presentation.components.auth

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.indianathe3rd.identity.presentation.ui.theme.TextSecondary
import com.indianathe3rd.identity.presentation.ui.theme.White

@Composable
fun RegisterHeader(isSignIn: MutableState<Boolean>){

    Text(
        text =if(isSignIn.value)"Sign Into You Account" else "Create An Account",
        style = MaterialTheme.typography.titleLarge,
        color = White,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(12.dp))
    Text(
        text = if (isSignIn.value) "Enter your Sign In details" else "Enter your personal data to create an account",
        style = MaterialTheme.typography.bodyMedium,
        color = White.copy(0.8f)
    )
}
