package com.example.mita.ui.screen.login.data

data class LoginUIState(
    var email : String= "",
    var password : String ="",

    var msgError : String ="",
    var emailMsgError : String ="",
    var passwordMsgError : String ="",

    var emailError: Boolean = false,
    var passwordError: Boolean = false,

    )
