package com.example.mita.ui.screen.register.data

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mita.navigation.MitaAppRouter
import com.example.mita.navigation.Screen
import com.example.mita.utils.Validator
import com.google.firebase.auth.FirebaseAuth

class RegisterViewModel : ViewModel() {

    var registerUIState = mutableStateOf(RegisterUIState())
    private var allValidationPassed = mutableStateOf(false)
    var registerInProgress = mutableStateOf(false)

    fun onEvent(event: RegisterUIEvent) {

        when (event) {
            is RegisterUIEvent.FirstNameChanged -> {
                registerUIState.value = registerUIState.value.copy(
                    firstName = event.firstName
                )
                if (registerUIState.value.firstName.isNotEmpty()) {
                    registerUIState.value = registerUIState.value.copy(
                        firstNameMsgError = ""
                    )
                }
            }

            is RegisterUIEvent.LastNameChanged -> {
                registerUIState.value = registerUIState.value.copy(
                    lastName = event.lastName
                )
                if (registerUIState.value.lastName.isNotEmpty()) {
                    registerUIState.value = registerUIState.value.copy(
                        lastNameMsgError = ""
                    )
                }
            }

            is RegisterUIEvent.EmailChanged -> {
                registerUIState.value = registerUIState.value.copy(
                    email = event.email
                )
                if (registerUIState.value.email.isNotEmpty()) {
                    registerUIState.value = registerUIState.value.copy(
                        emailMsgError = ""
                    )
                }
            }

            is RegisterUIEvent.PasswordChanged -> {
                registerUIState.value = registerUIState.value.copy(
                    password = event.password
                )
                if (registerUIState.value.password.isNotEmpty()) {
                    registerUIState.value = registerUIState.value.copy(
                        passwordMsgError = ""
                    )
                }
            }

            is RegisterUIEvent.PrivacyPolicyCheckBoxClicked -> {
                registerUIState.value = registerUIState.value.copy(
                    privacyPolicyAccepted = event.status
                )
            }

            is RegisterUIEvent.SignUpButtonClicked -> {
                if (validateDateWithRules()) {
                    signUp()
                }

            }
        }

    }

    private fun signUp() {
        createUserFirebase(
            email = registerUIState.value.email,
            password = registerUIState.value.password
        )

    }

    private fun validateDateWithRules(): Boolean {
        val fNameResult = Validator.validateFirsName(
            fName = registerUIState.value.firstName
        )
        val lNameResult = Validator.validateLastName(
            lName = registerUIState.value.lastName
        )
        val emailResult = Validator.validateEmail(
            emailValue = registerUIState.value.email
        )
        val passwordResult = Validator.validatePassword(
            passwordValue = registerUIState.value.password
        )

        val privacyPolicyCheckResult = Validator.validateCheckBox(
            statusValue = registerUIState.value.privacyPolicyAccepted
        )


        registerUIState.value = registerUIState.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            privacyPolicyError = privacyPolicyCheckResult.status

        )

        errorMsgForm()
        allValidationPassed.value = fNameResult.status
                && lNameResult.status
                && emailResult.status
                && passwordResult.status
                && privacyPolicyCheckResult.status

        return allValidationPassed.value
    }

    private fun errorMsgForm(): Boolean {
        registerUIState.value = registerUIState.value.copy(
            firstNameMsgError = "",
            lastNameMsgError = "",
            emailMsgError = "",
            passwordMsgError = "",
            privacyPolicyAcceptedMsgError = ""
        )

        if (!registerUIState.value.firstNameError) {

            registerUIState.value = registerUIState.value.copy(
                firstNameMsgError = "Please enter valid Name !"
            )
            return false
        }
        if (!registerUIState.value.lastNameError) {
            registerUIState.value = registerUIState.value.copy(
                lastNameMsgError = "Please enter valid last name !"
            )
            return false
        }
        if (!registerUIState.value.emailError) {

            registerUIState.value = registerUIState.value.copy(
                emailMsgError = "Please enter valid email !"

            )
            return false
        }
        if (!registerUIState.value.passwordError) {

            registerUIState.value = registerUIState.value.copy(
                passwordMsgError = "Please enter valid password !"
            )
            return false
        }

        if (!registerUIState.value.privacyPolicyError) {
            registerUIState.value = registerUIState.value.copy(
                privacyPolicyAcceptedMsgError = "Check Privacy Policy first !"
            )
            return false
        }
        return true
    }

    private fun createUserFirebase(email: String, password: String) {
        registerUIState.value.errorMsg = ""
        registerInProgress.value = true
        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                registerInProgress.value = false
                if (it.isSuccessful) {
                    MitaAppRouter.navigateTo(Screen.HomeScreen)
                }
            }
            .addOnFailureListener {
                registerInProgress.value = false
                registerUIState.value.errorMsg = it.localizedMessage.toString()
            }
    }
}

