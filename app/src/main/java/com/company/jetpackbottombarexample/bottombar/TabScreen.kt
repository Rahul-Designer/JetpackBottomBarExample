package com.company.jetpackbottombarexample.bottombar

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuite
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldLayout
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.window.core.layout.WindowWidthSizeClass
import com.company.jetpackbottombarexample.auth.navigation.NavigationCommand
import com.company.jetpackbottombarexample.auth.navigation.NavigationViewModel
import com.company.jetpackbottombarexample.booking.BookingNavigation
import com.company.jetpackbottombarexample.bottombar.tabs.ExploreScreen
import com.company.jetpackbottombarexample.bottombar.tabs.SettingScreen
import com.company.jetpackbottombarexample.main.home.HomeNavigation
import kotlin.collections.contains

@Composable
fun TabScreen(
    modifier: Modifier = Modifier,
    openInHomeDetailSeparate: (id: String) -> Unit,
    openBookingFlow: (BookingNavigation) -> Unit,
    navigationViewModel: NavigationViewModel
) {

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

    val adaptiveInfo = currentWindowAdaptiveInfo()
    val layoutType = with(adaptiveInfo){
        if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED)
            NavigationSuiteType.NavigationDrawer
        else{
            NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(adaptiveInfo)
        }
    }
    NavigationSuiteScaffold(
        layoutType = layoutType,
        navigationSuiteItems = {
            bottomBarItems.forEach { destination ->
                item(
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
        }, navigationSuiteColors = NavigationSuiteDefaults.colors(
            navigationDrawerContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            navigationBarContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            navigationRailContentColor = MaterialTheme.colorScheme.surfaceContainer,
        )
    ){
        NavDisplay(
            modifier = Modifier,
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },
            entryDecorators = listOf(
                rememberSavedStateNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ), entryProvider = entryProvider {
                entry<BottomBarScreen.HomeScreen> {
                    stateHolder.SaveableStateProvider(key = it.label) {
                        HomeNavigation(
                            openInHomeDetailSeparate = openInHomeDetailSeparate,
                            openBookingFlow = {
                                // Open booking from home screen
                                openBookingFlow(BookingNavigation.BookingScreen)
                            }
                        )
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

    /*
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
                        HomeNavigation(
                            openInHomeDetailSeparate = openInHomeDetailSeparate,
                            openBookingFlow = {
                                // Open booking from home screen
                                openBookingFlow(BookingNavigation.BookingScreen)
                            }
                        )
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

     */
}