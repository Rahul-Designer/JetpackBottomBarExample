package com.company.jetpackbottombarexample.auth.navigation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val navigationManager: NavigationManager
) : ViewModel() {

    val navigationCommands: SharedFlow<NavigationCommand> = navigationManager.navigationCommands

    fun navigate(command: NavigationCommand) {
        // You can also do additional processing here if needed
        navigationManager.navigate(command)
    }
}