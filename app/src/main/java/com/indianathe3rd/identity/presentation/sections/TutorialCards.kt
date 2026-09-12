package com.indianathe3rd.identity.presentation.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.indianathe3rd.identity.presentation.components.TutorialImage
import com.indianathe3rd.identity.presentation.components.TutorialStepHeader

@Composable
fun TutorialCards(step: String,caption: String,imagineId: Int){
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TutorialStepHeader(step, caption)
            TutorialImage(ImageResourceId = imagineId)
        }
    }
}