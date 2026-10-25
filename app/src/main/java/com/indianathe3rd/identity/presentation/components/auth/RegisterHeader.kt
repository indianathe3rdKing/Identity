package com.indianathe3rd.identity.presentation.components.auth

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.indianathe3rd.identity.presentation.ui.theme.TextSecondary
import com.indianathe3rd.identity.presentation.ui.theme.White

@Composable
fun RegisterHeader(isSignIn: MutableState<Boolean>){


    val flip by animateFloatAsState(
        targetValue = if (isSignIn.value) -1f else 1f,
        animationSpec = tween(durationMillis = 300, delayMillis = 50),
        label = "ComposableRotation",
    )
    val showSignIn = flip < 0f

    val title = if(showSignIn)"Sign Into You Account" else "Create An Account"
    val subTitle =  if (showSignIn) "Enter your Sign In details"  else "Enter your personal data to create an account"
    Text(
        text =title,
        style = MaterialTheme.typography.titleLarge,
        color = White,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .graphicsLayer(scaleX = flip)
            .graphicsLayer { scaleX = if (flip < 0f) -1f else 1f }

    )
    Spacer(modifier = Modifier.height(12.dp))
    Text(
        text =subTitle,
        style = MaterialTheme.typography.bodyMedium,
        color = White.copy(0.8f),
        modifier = Modifier
            .graphicsLayer(scaleX = flip)
            .graphicsLayer { scaleX = if (flip < 0f) -1f else 1f }

    )
}
