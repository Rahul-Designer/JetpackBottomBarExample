package com.company.jetpackbottombarexample.auth.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.jetpackbottombarexample.auth.authScreens.AuthScreenManager
import com.company.jetpackbottombarexample.auth.navigation.NavigationCommand
import com.company.jetpackbottombarexample.auth.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    fun updateEmail(email: String) {
        _email.value = email
        _errorMessage.value = null
    }

    fun updatePassword(password: String) {
        _password.value = password
        _errorMessage.value = null
    }

    fun login() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

//            val result = authRepository.login(email.value, password.value)

            _isLoading.value = false

            if (true) {
                navigationManager.navigate(
                    NavigationCommand.NavigateWithClear(AuthScreenManager.MainScreen)
                )
            } else {
                _errorMessage.value = "Login failed"
            }
        }
    }


    fun navigateToSignUp() {
        navigationManager.navigate(NavigationCommand.Navigate(AuthScreenManager.SignUpScreen))
    }
}