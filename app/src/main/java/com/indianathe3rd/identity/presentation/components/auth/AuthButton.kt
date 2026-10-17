package com.indianathe3rd.identity.presentation.components.auth

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.indianathe3rd.identity.presentation.ui.theme.White
import com.indianathe3rd.identity.domain.model.Result
import com.indianathe3rd.identity.presentation.ui.theme.TextPrimary
import com.indianathe3rd.identity.presentation.viewmodel.AuthViewModel

@Composable
fun AuthButton(isSignIn: MutableState<Boolean>, viewModel: AuthViewModel) {
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val result by viewModel.authState.collectAsState()
    Button(
        onClick = {
            if (isSignIn.value) viewModel.onSignIn() else
                viewModel.onSignUp()
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
        if (result is Result.Loading){
            Loading(result)
        }else{
        Text(
            text = if (isSignIn.value) "Sign in" else "Sign up",
            modifier = Modifier.padding(vertical = 4.dp), color = TextPrimary
        )}
    }

}