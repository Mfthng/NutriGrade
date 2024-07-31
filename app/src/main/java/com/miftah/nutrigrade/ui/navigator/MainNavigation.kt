package com.miftah.nutrigrade.ui.navigator

import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.miftah.nutrigrade.domain.Scanned
import com.miftah.nutrigrade.ui.home_screen.HomeScreen
import com.miftah.nutrigrade.ui.navgraph.Route
import com.miftah.nutrigrade.ui.navigator.component.BottomBar
import com.miftah.nutrigrade.ui.onboarding_screen.Onboardingscreen
import com.miftah.nutrigrade.ui.profile_screen.ProfileScreeen
import com.miftah.nutrigrade.ui.scan_screen.ScanScreen
import com.miftah.nutrigrade.ui.scan_screen.ScanViewModel
import com.miftah.nutrigrade.utils.Constanta.SCANNED_DATA

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar =  {
            if(navController.currentBackStackEntry?.destination?.route == Route.HomeScreen.route){
                BottomBar()
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            color = Color(0xffF6F6F6)
        ) {
            NavHost(navController = navController, startDestination = Route.ProfileScreen.route) {
                composable(route = Route.HomeScreen.route) {
                    HomeScreen(
                    )
                }

                composable(route = Route.ScanScreen.route) {
                    val viewModel : ScanViewModel = hiltViewModel()

                    ScanScreen(
                        modifier = Modifier,
                        state = viewModel.state.value,
                        onEvent = viewModel::onEvent,
                        navigate = {
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                key = SCANNED_DATA,
                                value = it
                            )
                            navController.navigate(Route.DetailScreen.route)
                        }
                    )
                }
                composable(route = Route.OnBoardingScreen.route){
                    Onboardingscreen(
                        onGettingStartedClick = {
                            navController.navigate(Route.HomeScreen.route)
                        },
                        onSkipClicked = {                             navController.navigate(Route.HomeScreen.route)
                        })
                }
                composable(route = Route.OnBoardingScreen.route){
                    Onboardingscreen(
                        onGettingStartedClick = {
                            navController.navigate(Route.HomeScreen.route)
                        },
                        onSkipClicked = {                             navController.navigate(Route.HomeScreen.route)
                        })
                }
                composable(route = Route.ProfileScreen.route){
                   ProfileScreeen(
                    modifier = Modifier
                   )
                }
                composable(route = Route.DetailScreen.route) {
                    val dataScan = navController.previousBackStackEntry?.savedStateHandle?.get<Scanned>(
                        SCANNED_DATA
                    )
                    Column {
                        if (dataScan == null){
                            Text (" no detail ")
                        } else {
                            Text(text = dataScan.grade)
                        }
                    }
                }
            }
        }
    }
}