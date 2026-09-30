package com.company.jetpackbottombarexample.auth.authScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.company.jetpackbottombarexample.auth.navigation.NavigationViewModel
import com.company.jetpackbottombarexample.booking.BookingNavigationScreen
import com.company.jetpackbottombarexample.bottombar.TabScreen
import com.company.jetpackbottombarexample.main.MainScreenNavigation

/*
@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    val navigationViewModel = hiltViewModel<NavigationViewModel>()

    val backStack = rememberNavBackStack<BottomBarScreen>(BottomBarScreen.HomeScreen)

    var currentBottomBarScreen: BottomBarScreen by rememberSaveable(
        stateSaver = BottomBarScreenSaver
    ) { mutableStateOf(BottomBarScreen.HomeScreen) }

    val stateHolder = rememberSaveableStateHolder()

    BackHandler(true) {
        if (currentBottomBarScreen != BottomBarScreen.HomeScreen) {
            // If not on HomeScreen, navigate to HomeScreen
            if (backStack.lastOrNull() in bottomBarItems) {
                backStack.removeAt(backStack.lastIndex)
            }
            backStack.add(BottomBarScreen.HomeScreen)
            currentBottomBarScreen = BottomBarScreen.HomeScreen
        } else {
            // If already on HomeScreen, navigate back to previous screen (exit MainScreen)
            navigationViewModel.navigate(NavigationCommand.NavigateBack)
        }
    }


    Scaffold(
        modifier = modifier
            .windowInsetsPadding(WindowInsets.statusBars),
        bottomBar = {
            NavigationBar {
                bottomBarItems.forEach { destination ->
                    NavigationBarItem(
                        selected = currentBottomBarScreen == destination,
                        icon = {
                            Icon(
                                imageVector = if (currentBottomBarScreen == destination) destination.selectedIcon else destination.icon,
                                contentDescription = "$destination icon"
                            )
                        },
                        label = {
                            Text(text = destination.label)
                        },
                        alwaysShowLabel = false,
                        onClick = {
                            if (backStack.lastOrNull() != destination) {
                                if (backStack.lastOrNull() in bottomBarItems) {
                                    backStack.removeAt(backStack.lastIndex)
                                }
                                backStack.add(destination)
                                currentBottomBarScreen = destination
                            }
                        }
                    )
                }
            }
        }) { innerPadding ->

        NavDisplay(
            modifier = Modifier.padding(innerPadding),
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryDecorators = listOf(
                rememberSavedStateNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ), entryProvider = entryProvider {
                entry<BottomBarScreen.HomeScreen> {
                    stateHolder.SaveableStateProvider(key = it.label) {
                        HomeNavigation()
                    }
                }
                entry<BottomBarScreen.ExploreScreen> {
                    ExploreScreen()
                }
                entry<BottomBarScreen.SettingScreen> {
                    SettingScreen()
                }
            }
        )

    }
}

 */

@Composable
fun MainScreen() {
    val navigationViewModel = hiltViewModel<NavigationViewModel>()
    val backStack = rememberNavBackStack(MainScreenNavigation.TabScreen)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<MainScreenNavigation.TabScreen> {
                TabScreen(
                    navigationViewModel = navigationViewModel,
                    openInHomeDetailSeparate = {
                        backStack.add(MainScreenNavigation.HomeSeparateScreen(it))
                    },
                    openBookingFlow = {
                        backStack.add(MainScreenNavigation.BookingFlow(it))
                    }
                )
            }
            entry<MainScreenNavigation.HomeSeparateScreen> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "List detail: ${it.id}")
                }
            }
            entry<MainScreenNavigation.BookingFlow> {
                BookingNavigationScreen(
                    modifier = Modifier.fillMaxSize(),
                    onBackToMain = {
                        backStack.clear()
                        backStack.add(MainScreenNavigation.TabScreen)
                    }
                )
            }
        }
    )

}