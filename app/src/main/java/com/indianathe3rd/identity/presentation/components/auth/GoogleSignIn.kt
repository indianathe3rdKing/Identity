package com.indianathe3rd.identity.presentation.components.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.indianathe3rd.identity.R
import com.indianathe3rd.identity.presentation.ui.theme.White

@Composable
fun GoogleSignIn(signIn: () -> Unit) {

    OutlinedButton(
        onClick = signIn

    ) {
        Image(
            painter = painterResource(R.drawable.icon_google),
            contentDescription = "Google Icon",
            modifier = Modifier
                .size(30.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "Sign In With Google",
            color = White,
            modifier = Modifier
                .padding(vertical = 4.dp)
        )
    }
}