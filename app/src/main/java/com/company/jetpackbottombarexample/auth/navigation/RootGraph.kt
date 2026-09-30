package com.company.jetpackbottombarexample.auth.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.company.jetpackbottombarexample.auth.authScreens.AuthScreenManager
import com.company.jetpackbottombarexample.auth.authScreens.MainScreen
import com.company.jetpackbottombarexample.auth.authScreens.SignInScreen
import com.company.jetpackbottombarexample.auth.authScreens.SignUpScreen
import com.company.jetpackbottombarexample.auth.authScreens.SplashScreen

@Composable
fun RootGraph() {
    val navigationViewModel = hiltViewModel<NavigationViewModel>()
    val backstack = rememberNavBackStack<AuthScreenManager>(AuthScreenManager.SplashScreen)
    // Handle navigation commands
    LaunchedEffect(Unit) {
        navigationViewModel.navigationCommands.collect { command ->
            when (command) {
                is NavigationCommand.Navigate -> {
//                    Simple navigation to a new screen while keeping previous screens in backstack.
                    backstack.add(command.screen)
                }

                is NavigationCommand.NavigateWithClear -> {
//                    Clear entire backstack and start fresh with new screen (similar to .finish() in Activities).
                    backstack.clear()
                    backstack.add(command.screen)
                }

                NavigationCommand.NavigateBack -> {
//                    Go back to previous screen (simulates back button press).
                    backstack.removeLastOrNull()
                }

                NavigationCommand.ClearBackstack -> {
//                    Clear all screens without adding a new one (rarely used alone).
                    backstack.clear()
                }
            }
        }
    }

    NavDisplay(
        backStack = backstack,
        onBack = { navigationViewModel.navigate(NavigationCommand.NavigateBack) },
        entryDecorators = listOf(
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<AuthScreenManager.SplashScreen> {
                SplashScreen()
            }
            entry<AuthScreenManager.SignInScreen> {
                SignInScreen()
            }
            entry<AuthScreenManager.SignUpScreen> {
                SignUpScreen()
            }
            entry<AuthScreenManager.MainScreen> {
                MainScreen()
            }

        },
        transitionSpec = {
            slideInHorizontally(initialOffsetX = { it }) togetherWith slideOutHorizontally(
                targetOffsetX = { -it })
        },
        popTransitionSpec = {
            slideInHorizontally(initialOffsetX = { -it }) togetherWith slideOutHorizontally(
                targetOffsetX = { it })
        }
    )

}
