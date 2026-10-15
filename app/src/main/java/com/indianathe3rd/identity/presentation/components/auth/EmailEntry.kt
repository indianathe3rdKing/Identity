package com.indianathe3rd.identity.presentation.components.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.indianathe3rd.identity.presentation.ui.theme.DarkGray
import com.indianathe3rd.identity.presentation.ui.theme.White
import com.indianathe3rd.identity.presentation.viewmodel.AuthViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun EmailField(viewModel: AuthViewModel){
    val email by viewModel.email.collectAsState()
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val localSoftwareKeyboardController = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier.bringIntoViewRequester(bringIntoViewRequester),
        horizontalAlignment = Alignment.Start
    ) {
        Text(

            text = "Email",
            color = White,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier
                .height(4.dp)
        )
        TextField(
            value = email,
            onValueChange = { newValue ->
                viewModel.onEmailChange(newValue)
            },
            placeholder = {
                Text(
                    text = "john.doe@example.com",
                    color = White.copy(0.7f)
                )
            },
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                focusedContainerColor = DarkGray,
                unfocusedContainerColor = DarkGray
            ),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusEvent { focusState ->
                    if (focusState.isFocused) {
                        coroutineScope.launch {
                            delay(300)
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(androidx.compose.ui.focus.FocusDirection.Down)
                }
            )
        )
    }
}