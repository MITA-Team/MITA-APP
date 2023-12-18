package com.example.mita

import android.app.Application
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
import com.google.firebase.FirebaseApp

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
            if (currentRoute != Screen.WelcomeScreen.toString() &&
                currentRoute != Screen.LoginScreen.toString() &&
                currentRoute != Screen.RegisterScreen.toString()) {
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
                startDestination = Screen.WelcomeScreen.toString(),
                route = "main"
            ){
                composable(route = Screen.HomeScreen.toString()) {
                    HomeScreen(navController)
                }
                composable(route = Screen.ActivityScreen.toString()) {
                    ActivityScreen()
                }
                composable(
                    route = Screen.ProfileScreen.toString()
                ) {
                    ProfileScreen()
                }
            }

            //without bottom bar
            navigation(
                startDestination = Screen.WelcomeScreen.toString(),
                route = "authentication"
            ) {
                composable(
                    route = Screen.WelcomeScreen.toString()
                ) {
                    WelcomeScreen(navController)
                }
                composable(
                    route = "LoginScreen"
                ) {
                    LoginScreen()
                }
                composable(
                    route = Screen.RegisterScreen.toString()
                ) {
                    RegisterScreen(navController)
                }
            }
        }
    }
}

class MITAApp: Application(){
    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
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
                screen = Screen.HomeScreen
            ),
            NavigationItem(
                title = stringResource(R.string.menu_activity),
                icon = Icons.Default.Face,
                screen = Screen.ActivityScreen
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.Person,
                screen = Screen.ProfileScreen
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
                selected = currentRoute == item.screen.toString(),
                onClick = {
                    navController.navigate(item.screen.toString()) {
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