package com.indianathe3rd.identity.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indianathe3rd.identity.domain.model.SignInRequest
import com.indianathe3rd.identity.domain.model.SignUpRequest
import com.indianathe3rd.identity.domain.repository.supabase.AuthRepository
import com.indianathe3rd.identity.domain.usecase.supabase.auth.SignInUsecase
import com.indianathe3rd.identity.domain.usecase.supabase.auth.SignInWithGoogle
import com.indianathe3rd.identity.domain.usecase.supabase.auth.SignUpUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInCase: SignInUsecase,
    private val signUpCase: SignUpUsecase,
    private val signInWithGoogle: SignInWithGoogle
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email = _email.asSharedFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asSharedFlow()

    private val _username = MutableStateFlow("")
    val username = _username

    private val _avatar_url = MutableStateFlow("")
    val avatar_url = _avatar_url.asSharedFlow()

    private val _profile = mutableStateOf(
        SignUpRequest(
            email = _email.value,
            username = _username.value,
            password = _password.value,
            avatar = _avatar_url.value
        )
    )

    val profile = _profile

    private val _authCredentials = mutableStateOf(
        SignInRequest(
            email = _email.value,
            password = _password.value
        )
    )

    fun onEmailChange(email: String) {
        _email.value = email
    }

    fun onPasswordChange(password: String) {
        _password.value = password
    }

    fun onSignUp() {
        viewModelScope.launch {
            signUpCase(
                _authCredentials.value
            )
        }
    }

    fun onSignIn() {
        viewModelScope.launch {
            signInCase(
                _authCredentials.value
            )
        }
    }

    fun onSignInWithGoogle() {
        viewModelScope.launch {
            signInWithGoogle()
        }
    }
}