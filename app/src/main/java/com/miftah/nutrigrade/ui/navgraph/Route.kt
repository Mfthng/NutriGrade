package com.miftah.nutrigrade.ui.navgraph

sealed class Route(
    val route: String
) {
    data object MainNavigator : Route("main_navigator")

    data object EmptyGraph : Route("empty_graph")

    data object HomeScreen : Route("home_screen")
    data object ScanScreen  : Route("scan_screen")
    data object SettingScreen  : Route("setting_screen")

}