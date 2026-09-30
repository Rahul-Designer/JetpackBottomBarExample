package com.company.jetpackbottombarexample.main.home

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.company.jetpackbottombarexample.bottombar.tabs.HomeMainScreen

@Composable
fun HomeNavigation(
    openInHomeDetailSeparate: (id: String) -> Unit,
    openBookingFlow: () -> Unit
) {
    val backStack = rememberNavBackStack<HomeScreenNavigator>(HomeScreenNavigator.HomeMainScreen)
    BackHandler() {
        backStack.removeLastOrNull()
    }
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {

            entry<HomeScreenNavigator.HomeMainScreen> {
                HomeMainScreen(openInHomeDetailOne = {
                    backStack.add(HomeScreenNavigator.HomeScreenOne("Home One"))
                }, openInHomeDetailTwo = {
                    backStack.add(HomeScreenNavigator.HomeScreenTwo("Home Two"))
                }, openInHomeDetailSeparate = {
                    openInHomeDetailSeparate(it)
                },openBookingFlow = {
                    openBookingFlow()
                })
            }

            entry<HomeScreenNavigator.HomeScreenOne> {
                HomeDetailOne(name = it.name, onBack = {
                    backStack.removeLastOrNull()
                })
            }

            entry<HomeScreenNavigator.HomeScreenTwo> {
                HomeDetailTwo(name = it.name)
            }
        }

    )
}