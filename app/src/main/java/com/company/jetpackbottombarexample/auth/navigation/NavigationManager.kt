package com.company.jetpackbottombarexample.auth.navigation

import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

interface NavigationManager {
    fun navigate(command: NavigationCommand)
    val navigationCommands: SharedFlow<NavigationCommand>
}

@Singleton
class NavigationManagerImpl @Inject constructor() : NavigationManager {
    private val _navigationCommands = MutableSharedFlow<NavigationCommand>(
        extraBufferCapacity = Channel.UNLIMITED
    )

    override val navigationCommands: SharedFlow<NavigationCommand> =
        _navigationCommands.asSharedFlow()

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    override fun navigate(command: NavigationCommand) {
        coroutineScope.launch {
            _navigationCommands.emit(command)
        }
    }
}