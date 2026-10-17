package com.indianathe3rd.identity.presentation.components.auth

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.indianathe3rd.identity.R
import com.indianathe3rd.identity.domain.model.Result

@Composable
fun Loading(result: Result){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    val progress by animateLottieCompositionAsState(
        composition= composition,
        iterations = LottieConstants.IterateForever
    )

    if (result is Result.Loading){
        LottieAnimation(
            modifier = Modifier
                .size(40.dp),
            composition = composition,
            progress = {progress}
        )
    }
}