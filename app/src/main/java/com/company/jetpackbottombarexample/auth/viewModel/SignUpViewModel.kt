package com.company.jetpackbottombarexample.auth.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.jetpackbottombarexample.auth.authScreens.AuthScreenManager
import com.company.jetpackbottombarexample.auth.navigation.NavigationCommand
import com.company.jetpackbottombarexample.auth.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
) : ViewModel() {

    // Form fields
    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword = _confirmPassword.asStateFlow()

    // UI State
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    private val _validationErrors = MutableStateFlow<Map<String, String>>(emptyMap())
    val validationErrors = _validationErrors.asStateFlow()

    val isFormValid = combine(name, email, password, confirmPassword) { name, email, password, confirmPassword ->
        name.isNotBlank() &&
                email.isNotBlank() && email.isValidEmail() &&
                password.isNotBlank() && password.length >= 6 &&
                confirmPassword == password
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        false
    )
    // Update methods
    fun updateName(name: String) {
        _name.value = name
        clearValidationError("name")
    }

    fun updateEmail(email: String) {
        _email.value = email
        clearValidationError("email")
    }

    fun updatePassword(password: String) {
        _password.value = password
        clearValidationError("password")
    }

    fun updateConfirmPassword(confirmPassword: String) {
        _confirmPassword.value = confirmPassword
        clearValidationError("confirmPassword")
    }

    // Clear specific validation error
    private fun clearValidationError(field: String) {
        _validationErrors.update { errors ->
            errors.toMutableMap().apply {
                remove(field)
            }
        }
    }

    // Form validation
    private fun validateForm(): Boolean {
        val errors = mutableMapOf<String, String>()

        if (name.value.isBlank()) {
            errors["name"] = "Name is required"
        }

        if (email.value.isBlank()) {
            errors["email"] = "Email is required"
        } else if (!email.value.isValidEmail()) {
            errors["email"] = "Please enter a valid email"
        }

        if (password.value.isBlank()) {
            errors["password"] = "Password is required"
        } else if (password.value.length < 6) {
            errors["password"] = "Password must be at least 6 characters"
        }

        if (confirmPassword.value.isBlank()) {
            errors["confirmPassword"] = "Please confirm your password"
        } else if (password.value != confirmPassword.value) {
            errors["confirmPassword"] = "Passwords do not match"
        }

        _validationErrors.value = errors
        return errors.isEmpty()
    }

    // Sign up
    fun signUp() {
        /*
        if (!validateForm()) {
            return
        }

         */

        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            /*
            val result = authRepository.register(
                name = name.value,
                email = email.value,
                password = password.value
            )

             */
            val result = true

            _isLoading.value = false

            if (result) {
                // Navigate to main screen on successful registration
                navigationManager.navigate(
                    NavigationCommand.NavigateWithClear(AuthScreenManager.MainScreen)
                )
            } else {
//                val error = result.exceptionOrNull()
                _errorMessage.value = "Registration failed"

                // Handle specific API errors
//                handleApiError(error)
            }
        }
    }

    /*
    private fun handleApiError(error: Throwable?) {
        when (error) {
            is HttpException -> {
                when (error.code()) {
                    409 -> _errorMessage.value = "Email already exists"
                    400 -> _errorMessage.value = "Invalid registration data"
                    500 -> _errorMessage.value = "Server error. Please try again later"
                    else -> _errorMessage.value = "Registration failed. Please try again"
                }
            }
            is IOException -> {
                _errorMessage.value = "Network error. Please check your connection"
            }
            else -> {
                _errorMessage.value = error?.message ?: "Registration failed"
            }
        }
    }

     */

    // Navigate back to sign in
    fun navigateToSignIn() {
        navigationManager.navigate(NavigationCommand.NavigateBack)
    }

    // Clear all errors
    fun clearErrors() {
        _errorMessage.value = null
        _validationErrors.value = emptyMap()
    }
}

private fun String.isValidEmail(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}