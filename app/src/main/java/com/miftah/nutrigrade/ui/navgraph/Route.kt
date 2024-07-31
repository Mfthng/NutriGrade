package com.miftah.nutrigrade.ui.navgraph

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

sealed class Route(
    val route: String
) {
    data object MainNavigator : Route("main_navigator")

    data object EmptyGraph : Route("empty_graph")

    data object HomeScreen : Route("home_screen")
    data object ScanScreen  : Route("scan_screen")
    data object SettingScreen  : Route("setting_screen")
    data object DetailScreen  : Route("detail_screen")
}

fun NavHostController.navigateWithBundle(
    route: String,
    args: Bundle,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    val nodeId = graph.findNode(route = route)?.id
    if (nodeId != null) {
        navigate(nodeId, args, navOptions, navigatorExtras)
    }
}