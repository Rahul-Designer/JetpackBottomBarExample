package com.company.jetpackbottombarexample.booking

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.company.jetpackbottombarexample.booking.screens.BookingDetailScreen
import com.company.jetpackbottombarexample.booking.screens.BookingScreen
import kotlinx.coroutines.coroutineScope

@Composable
fun BookingNavigationScreen(modifier: Modifier = Modifier, onBackToMain: () -> Unit) {

    val backStack = rememberNavBackStack(BookingNavigation.BookingScreen)
    
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<BookingNavigation.BookingScreen> {
                BookingScreen(
                    onNavigateBack = {
                        onBackToMain()
                    }, onDetailScreen = {
                        // show side panel
                        backStack.add(BookingNavigation.BookingDetailScreen("1"))
                    }, onBookButton = {
                        // open s

                    }
                )
            }

            entry<BookingNavigation.BookingDetailScreen> {
                BookingDetailScreen(
                    id = it.id,
                    onNavigateBack = {
                        backStack.removeLastOrNull()
                    },
                    onNavigateToMain = {
                        onBackToMain()
                    }
                )
            }

        },
        transitionSpec = {
            scaleIn(
                initialScale = 0.8f,
                animationSpec = tween(300)
            ) + fadeIn(animationSpec = tween(300)) togetherWith
                    scaleOut(
                        targetScale = 0.8f,
                        animationSpec = tween(300)
                    ) + fadeOut(animationSpec = tween(300))
        },
        popTransitionSpec = {
            scaleIn(
                initialScale = 0.8f,
                animationSpec = tween(300)
            ) + fadeIn(animationSpec = tween(300)) togetherWith
                    scaleOut(
                        targetScale = 0.8f,
                        animationSpec = tween(300)
                    ) + fadeOut(animationSpec = tween(300))
        }
    )
}