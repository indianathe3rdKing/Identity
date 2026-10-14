package com.indianathe3rd.identity.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indianathe3rd.identity.domain.model.Result
import com.indianathe3rd.identity.domain.model.SignInRequest
import com.indianathe3rd.identity.domain.model.SignUpRequest
import com.indianathe3rd.identity.domain.repository.supabase.AuthRepository
import com.indianathe3rd.identity.domain.usecase.supabase.auth.SignInUsecase
import com.indianathe3rd.identity.domain.usecase.supabase.auth.SignInWithGoogle
import com.indianathe3rd.identity.domain.usecase.supabase.auth.SignUpUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val signInCase: SignInUsecase,
    private val signUpCase: SignUpUsecase,
    private val signInWithGoogle: SignInWithGoogle
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _username = MutableStateFlow("")
    val username = _username

    private val _avatar_url = MutableStateFlow("")
    val avatar_url = _avatar_url.asStateFlow()

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

    private val _authState = MutableStateFlow<Result>(Result.Idle)
    val authState = _authState.asStateFlow()

    init {
        viewModelScope.launch {
            authRepository.sessionStatus.collect { status ->
                if (status is SessionStatus.Authenticated) {
                    _authState.value = Result.Success
                }
            }
        }
    }


    fun onEmailChange(email: String) {
        _email.value = email
        _authCredentials.value = _authCredentials.value.copy(email = email)
        _profile.value = _profile.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _password.value = password
        _authCredentials.value = _authCredentials.value.copy(password = password)
        _profile.value = _profile.value.copy(password = password)
    }

    fun onSignUp() {
        viewModelScope.launch {
            _authState.value = Result.Loading
          val result = signUpCase(
                _authCredentials.value
            )
            _authState.value = result
        }
    }

    fun onSignIn() {
        viewModelScope.launch {
            _authState.value = Result.Loading
           val result = signInCase(
                _authCredentials.value
            )
            _authState.value = result
        }
    }

    fun onSignInWithGoogle() {
        viewModelScope.launch {
            _authState.value = Result.Loading
            val result = signInWithGoogle()
            if (result is Result.Error) {
                _authState.value = result
            } else {
                _authState.value = Result.Idle
            }
        }
    }
}