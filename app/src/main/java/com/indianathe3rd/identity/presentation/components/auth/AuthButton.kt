package com.indianathe3rd.identity.presentation.components.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
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
    var animating by remember { mutableStateOf(false) }
    var displayText by remember { mutableStateOf(false) }
    val scaleUp by animateFloatAsState(
        targetValue = if (animating) 1.2f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),

        label = "ScaleUp"
    )
    val flip by animateFloatAsState(
        targetValue = if (isSignIn.value) -1f else 1f,
        animationSpec = tween(durationMillis = 300, delayMillis = 50),
        label = "ComposableRotation",
        finishedListener = {
            animating = false
        },
    )
    LaunchedEffect(flip) {
        displayText = flip <= 0f
    }

    Button(
        onClick = {
            animating = true
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
            .graphicsLayer(
                scaleX = flip,
                scaleUp
            )

    ) {
        if (result is Result.Loading) {
            Loading(result)
        } else {
            if (isSignIn.value && flip <= 0f) {
                Text(
                    text = "Sign in",
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .graphicsLayer(
                            scaleX = if (flip < 0f) -1f else 1f
                        ), color = TextPrimary
                )
            }
            if (!isSignIn.value && flip > 0f) {
                Text(
                    text = "Sign up",
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        , color = TextPrimary
                )
            }
        }
    }


}