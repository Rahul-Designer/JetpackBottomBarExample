package com.company.jetpackbottombarexample.auth.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.jetpackbottombarexample.auth.authScreens.AuthScreenManager
import com.company.jetpackbottombarexample.auth.navigation.NavigationCommand
import com.company.jetpackbottombarexample.auth.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val navigationManager: NavigationManager
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        checkAuthStatus()
    }

    private fun checkAuthStatus() {
        viewModelScope.launch {
            try {
                // Simulate minimum splash time (optional)
                delay(2000)

                val isAuthenticated = true

                val destination = if (isAuthenticated) {
                    AuthScreenManager.MainScreen
                } else {
                    AuthScreenManager.SignInScreen
                }

                navigationManager.navigate(
                    NavigationCommand.NavigateWithClear(destination)
                )

            } catch (e: Exception) {
                // On error, go to sign in
                navigationManager.navigate(
                    NavigationCommand.NavigateWithClear(AuthScreenManager.SignInScreen)
                )
            } finally {
                _isLoading.value = false
            }
        }
    }
}