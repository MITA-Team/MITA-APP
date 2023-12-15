package com.example.mita.ui.screen.register.data

sealed class RegisterUIEvent {
    data class FirstNameChanged(val firstName : String): RegisterUIEvent()
    data class LastNameChanged(val lastName : String): RegisterUIEvent()
    data class EmailChanged(val email : String): RegisterUIEvent()
    data class PasswordChanged(val password : String): RegisterUIEvent()
    data class PrivacyPolicyCheckBoxClicked(val status : Boolean): RegisterUIEvent()
    object SignUpButtonClicked : RegisterUIEvent()
}