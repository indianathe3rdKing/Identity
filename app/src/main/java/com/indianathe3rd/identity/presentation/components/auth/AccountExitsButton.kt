package com.indianathe3rd.identity.presentation.components.auth

import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import com.indianathe3rd.identity.presentation.ui.theme.White

@Composable
fun AccountExitsButton(isSignIn: MutableState<Boolean>){

    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    TextButton(
        onClick = {
            isSignIn.value = !isSignIn.value

        }
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Light,
                        color = White.copy(0.8f)
                    )
                ) {
                    if(!isSignIn.value) {
                        append("Already have an account? ")
                    }else{
                        append("Don't have account")
                    }
                }
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = White
                    )
                ) {
                    if (!isSignIn.value){
                    append(" Log in")}else{
                        append(" Sign up")
                    }
                }

            },
            modifier = Modifier
                .bringIntoViewRequester(bringIntoViewRequester)
        )
    }
}