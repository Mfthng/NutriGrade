package com.miftah.nutrigrade.ui.navigator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.miftah.nutrigrade.ui.home_screen.HomeScreen
import com.miftah.nutrigrade.ui.navgraph.Route
import com.miftah.nutrigrade.ui.scan_screen.ScanScreen
import com.miftah.nutrigrade.ui.scan_screen.ScanViewModel

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {
            NavHost(navController = navController, startDestination = Route.ScanScreen.route) {
                composable(route = Route.HomeScreen.route) {
                    HomeScreen()
                }
                composable(route = Route.ScanScreen.route) {
                    val viewModel : ScanViewModel = hiltViewModel()
                    ScanScreen(
                        modifier = Modifier,
                        state = viewModel.state.value,
                        onEvent = viewModel::onEvent
                    )
                }

            }
        }
    }
}