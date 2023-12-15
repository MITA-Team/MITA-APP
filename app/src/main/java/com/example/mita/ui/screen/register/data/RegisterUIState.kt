package com.example.mita.ui.screen.register.data

data class RegisterUIState (
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var password: String = "",
    var privacyPolicyAccepted: Boolean = false,
    var errorMsg: String? =null,

    var firstNameMsgError : String ="",
    var lastNameMsgError : String ="",
    var emailMsgError : String ="",
    var passwordMsgError : String ="",
    var privacyPolicyAcceptedMsgError : String ="",

    var firstNameError: Boolean = false,
    var lastNameError: Boolean = false,
    var emailError: Boolean = false,
    var passwordError: Boolean = false,
    var privacyPolicyError: Boolean = false
)