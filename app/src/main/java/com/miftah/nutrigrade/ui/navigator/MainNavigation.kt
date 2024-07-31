package com.miftah.nutrigrade.ui.navigator

import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.miftah.nutrigrade.domain.Scanned
import com.miftah.nutrigrade.ui.detail_screen.DetailScreen
import com.miftah.nutrigrade.ui.detail_screen.DetailViewModel
import com.miftah.nutrigrade.ui.home_screen.HomeScreen
import com.miftah.nutrigrade.ui.navgraph.Route
import com.miftah.nutrigrade.ui.navigator.component.BottomBar
import com.miftah.nutrigrade.ui.onboarding_screen.Onboardingscreen
import com.miftah.nutrigrade.ui.profile_screen.ProfileScreen
import com.miftah.nutrigrade.ui.scan_screen.ScanScreen
import com.miftah.nutrigrade.ui.scan_screen.ScanViewModel
import com.miftah.nutrigrade.utils.Constanta.SCANNED_DATA

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        bottomBar = {
            if (navBackStackEntry?.destination?.route == Route.HomeScreen.route || navBackStackEntry?.destination?.route == Route.SettingScreen.route) {
                BottomBar(navController, fabOnClick = {
                    navController.navigate(Route.ScanScreen.route)
                })
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            color = Color(0xffF6F6F6)
        ) {
            NavHost(
                navController = navController,
                startDestination = Route.HomeScreen.route
            ) {
                composable(route = Route.HomeScreen.route) {
                    HomeScreen(

                    )
                }

                composable(Route.ProfileScreen.route) {
                    ProfileScreen(
                        modifier = Modifier
                    )
                }

                composable(route = Route.ScanScreen.route) {
                    val viewModel: ScanViewModel = hiltViewModel()

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
                composable(route = Route.OnBoardingScreen.route) {
                    Onboardingscreen(
                        onGettingStartedClick = {
                            navController.navigate(Route.HomeScreen.route)
                        },
                        onSkipClicked = {
                            navController.navigate(Route.HomeScreen.route)
                        })
                }
                composable(route = Route.DetailScreen.route) {
                    var dataScan =
                        navController.previousBackStackEntry?.savedStateHandle?.get<Scanned>(
                            SCANNED_DATA
                        )
                    val viewModel: DetailViewModel = hiltViewModel()

                    if (dataScan == null) {
                        dataScan = Scanned(
                            productName = "a",
                            dietaryFiber100g = 1,
                            positiveFeedback = "s",
                            protein100g = 1,
                            totalFat100g = 2,
                            dietaryFiber = 3,
                            totalCarbs = 1,
                            productPhoto = "1",
                            nutriScore = 1,
                            cholesterol = 1,
                            portionSize = 1,
                            sugars100g = 1,
                            warnings = "",
                            sodium100g = 1,
                            energy100g = 1,
                            totalCarbs100g = 1,
                            totalFat = 1,
                            grade = "",
                            energy = 1,
                            sugars = 1,
                            sodium = 1,
                            portionSize100g = "",
                            protein = 1
                        )
                        viewModel.setScanned(dataScan)
                    } else {
                        viewModel.setScanned(dataScan)
                    }
                    DetailScreen(
                        onEvent = viewModel::onEvent,
                        state = viewModel.state.value,
                        navigate = {
                            navController.navigate(Route.HomeScreen.route)
                        }
                    )
                }
            }
        }
    }
}