package com.company.jetpackbottombarexample.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.saveable.Saver
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface BottomBarScreen : NavKey{
    val screen: BottomBarScreen
    val label: String
    val icon: ImageVector
    val selectedIcon: ImageVector

    @Serializable
    data object HomeScreen : BottomBarScreen {
        override val screen: BottomBarScreen = HomeScreen
        override val label: String = "Home"
        override val icon: ImageVector = Icons.Outlined.Home
        override val selectedIcon: ImageVector = Icons.Filled.Home
    }

    @Serializable
    data object ExploreScreen : BottomBarScreen {
        override val screen: BottomBarScreen = ExploreScreen
        override val label: String = "Explore"
        override val icon: ImageVector = Icons.Outlined.Explore
        override val selectedIcon: ImageVector = Icons.Filled.Explore
    }

    @Serializable
    data object SettingScreen : BottomBarScreen{
        override val screen: BottomBarScreen = SettingScreen
        override val label: String = "Setting"
        override val icon: ImageVector = Icons.Outlined.Settings
        override val selectedIcon: ImageVector = Icons.Filled.Settings
    }

    companion object {
        val items = listOf(HomeScreen, ExploreScreen, SettingScreen)

        val stateSaver = Saver<BottomBarScreen, String>(
            save = { it::class.qualifiedName },
            restore = { qualifiedClass ->
                items.firstOrNull { it::class.qualifiedName == qualifiedClass } ?: HomeScreen
            }
        )
    }
}

val bottomBarItems = listOf<BottomBarScreen>(
    BottomBarScreen.HomeScreen,
    BottomBarScreen.ExploreScreen,
    BottomBarScreen.SettingScreen
)

val BottomBarScreenSaver = Saver<BottomBarScreen, String>(
    save = { it::class.simpleName ?: "Unknown" },
    restore = {
        when (it) {
            BottomBarScreen.HomeScreen::class.simpleName -> BottomBarScreen.HomeScreen
            BottomBarScreen.ExploreScreen::class.simpleName -> BottomBarScreen.ExploreScreen
            BottomBarScreen.SettingScreen::class.simpleName -> BottomBarScreen.SettingScreen
            else -> BottomBarScreen.HomeScreen
        }
    }
)