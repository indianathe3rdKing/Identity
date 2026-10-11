package com.indianathe3rd.identity.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indianathe3rd.identity.domain.model.SignUpRequest
import com.indianathe3rd.identity.domain.repository.supabase.AuthRepository
import com.indianathe3rd.identity.domain.usecase.supabase.auth.SignInUsecase
import com.indianathe3rd.identity.domain.usecase.supabase.auth.SignUpUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInCase: SignInUsecase,
    private val signUpCase: SignUpUsecase
): ViewModel() {

    private val _email = MutableStateFlow("")
    val email: Flow<String> = _email

    private val _password = MutableStateFlow("")
    val password: Flow<String> = _password

    private val _username = MutableStateFlow("")
    val username = _username

    private val _avatar_url = MutableStateFlow("")
    val avatar_url = _avatar_url

    private val _profile = mutableStateOf(SignUpRequest(
        email = _email.value,
        username = _username.value,
        password = _password.value,
        avatar = _avatar_url.value
    ))
    val profile = _profile

    fun onEmailChange(email: String){
        _email.value = email
    }

    fun onPasswordChange(password: String){
        _password.value = password
    }

    fun onSignUp(){
        viewModelScope.launch {
           signUpCase(
             profile.value
           )
        }
    }
}