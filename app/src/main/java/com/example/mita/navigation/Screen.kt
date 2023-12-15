package com.example.mita.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen () {
    object WelcomeScreen: Screen()
    object LoginScreen: Screen()
    object RegisterScreen: Screen()
    object HomeScreen: Screen()
    object ActivityScreen: Screen()
    object ProfileScreen: Screen()
    object QuestionScreen: Screen()
    object ResultScreen: Screen()
    object TherapyScreen: Screen()
    object TermsAndConditionsScreen : Screen()
}

object MitaAppRouter{

    private var currentScreen: MutableState<Screen> = mutableStateOf(Screen.LoginScreen)

    fun navigateTo(destination : Screen){
        currentScreen.value = destination
    }
}