package com.indianathe3rd.identity.presentation.components.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush

import com.indianathe3rd.identity.presentation.ui.theme.Black
import com.indianathe3rd.identity.presentation.ui.theme.DarkPurple
import com.indianathe3rd.identity.presentation.ui.theme.Purple

@Composable
fun Gradient() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.35f)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Purple,
                        DarkPurple,
                        Black
                    )
                ))
    )
}