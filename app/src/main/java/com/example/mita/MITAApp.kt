package com.example.mita

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.mita.navigation.NavigationItem
import com.example.mita.navigation.Screen
import com.example.mita.ui.screen.activity.ActivityScreen
import com.example.mita.ui.screen.home.HomeScreen
import com.example.mita.ui.screen.login.LoginScreen
import com.example.mita.ui.screen.profile.ProfileScreen
import com.example.mita.ui.screen.register.RegisterScreen
import com.example.mita.ui.screen.welcome.WelcomeScreen

@Composable
fun MITAApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (currentRoute != Screen.Welcome.route &&
                currentRoute != Screen.Login.route &&
                currentRoute != Screen.Register.route) {
                BottomBar(navController)
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "authentication",
            modifier = Modifier
                .padding(innerPadding)
                .statusBarsPadding()
        ) {
            navigation(
                startDestination = Screen.Welcome.route,
                route = "main"
            ){
                composable(route = Screen.Home.route) {
                    HomeScreen()
                }
                composable(route = Screen.Activity.route) {
                    ActivityScreen()
                }
                composable(
                    route = Screen.Profile.route
                ) {
                    ProfileScreen()
                }
            }

            //without bottom bar
            navigation(
                startDestination = Screen.Welcome.route,
                route = "authentication"
            ) {
                composable(
                    route = Screen.Welcome.route
                ) {
                    WelcomeScreen(navController)
                }
                composable(
                    route = Screen.Login.route
                ) {
                    LoginScreen(navController)
                }
                composable(
                    route = Screen.Register.route
                ) {
                    RegisterScreen(navController)
                }
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_activity),
                icon = Icons.Default.Face,
                screen = Screen.Activity
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.Person,
                screen = Screen.Profile
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun MITAAppPreview(){
    MITAApp()
}