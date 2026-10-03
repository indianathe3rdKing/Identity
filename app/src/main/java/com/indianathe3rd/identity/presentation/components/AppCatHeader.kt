package com.indianathe3rd.identity.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.indianathe3rd.identity.R
import com.indianathe3rd.identity.presentation.ui.theme.SoftTextTertiary
import com.indianathe3rd.identity.presentation.ui.theme.TextPrimary

@Composable
fun AppCatHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.icon),
                contentDescription = "Category Icon",
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(40.dp)
                    )
    ,contentScale= ContentScale.Fit
            )
            Column() {
                Text(
                    text = "What defined you today?",
                    style = MaterialTheme.typography.titleMedium,
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Time spent across app categories",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextPrimary.copy(alpha = 0.6f)
                )
            }
        }

        Icon(
            Icons.Outlined.ArrowForwardIos,
            contentDescription = "Arrow Forward",
            tint = TextPrimary.copy(alpha = 0.6f),
            modifier = Modifier
                .size(20.dp)

        )
    }
}