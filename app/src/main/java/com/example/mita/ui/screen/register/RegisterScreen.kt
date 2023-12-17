package com.example.mita.ui.screen.register

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mita.R
import com.example.mita.navigation.MitaAppRouter
import com.example.mita.navigation.Screen
import com.example.mita.navigation.SystemBackButtonHandler
import com.example.mita.ui.component.ButtonComponent
import com.example.mita.ui.component.CheckBoxComponent
import com.example.mita.ui.component.ClickableLoginTextComponent
import com.example.mita.ui.component.DividerTextComponent
import com.example.mita.ui.component.HeadingTextComponent
import com.example.mita.ui.component.NormalTextComponent
import com.example.mita.ui.component.PasswordTextFieldComponent
import com.example.mita.ui.component.TextFieldComponent
import com.example.mita.ui.screen.register.data.RegisterUIEvent
import com.example.mita.ui.screen.register.data.RegisterViewModel

@Composable
fun RegisterScreen(
    navController: NavHostController,
    registerViewModel: RegisterViewModel = viewModel()
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                NormalTextComponent(value = stringResource(id = R.string.app_name))
                HeadingTextComponent(value = stringResource(id = R.string.create_account))
                Spacer(modifier = Modifier.height(20.dp))
                TextFieldComponent(
                    labelValue = stringResource(id = R.string.first_name),
                    painterResource = painterResource(id = R.drawable.baseline_person_24),
                    keyboardType = KeyboardType.Text,
                    onTextSelected = {
                        registerViewModel.onEvent(RegisterUIEvent.FirstNameChanged(it))
                    },
                    errorStatus = registerViewModel.registerUIState.value.firstNameError,
                    errorMsg = registerViewModel.registerUIState.value.firstNameMsgError
                )

                TextFieldComponent(
                    labelValue = stringResource(id = R.string.last_name),
                    painterResource = painterResource(id = R.drawable.baseline_person_24),
                    keyboardType = KeyboardType.Text,
                    onTextSelected = {
                        registerViewModel.onEvent(RegisterUIEvent.LastNameChanged(it))

                    },
                    errorStatus = registerViewModel.registerUIState.value.lastNameError,
                    errorMsg = registerViewModel.registerUIState.value.lastNameMsgError
                )

                TextFieldComponent(
                    labelValue = stringResource(id = R.string.email),
                    painterResource = painterResource(id = R.drawable.baseline_email_24),
                    keyboardType = KeyboardType.Email,
                    onTextSelected = {
                        registerViewModel.onEvent(RegisterUIEvent.EmailChanged(it))

                    },
                    errorStatus = registerViewModel.registerUIState.value.emailError,
                    errorMsg = registerViewModel.registerUIState.value.emailMsgError

                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource = painterResource(id = R.drawable.baseline_lock_24),
                    onTextSelected = {
                        registerViewModel.onEvent(RegisterUIEvent.PasswordChanged(it))
                    },
                    errorStatus = registerViewModel.registerUIState.value.passwordError,
                    errorMsg = registerViewModel.registerUIState.value.passwordMsgError

                )

                CheckBoxComponent(value = stringResource(id = R.string.trim_continuing),
                    onTextSelected = {
                        MitaAppRouter.navigateTo(Screen.TermsAndConditionsScreen)
                    }, onCheckedChange = {
                        registerViewModel.onEvent(RegisterUIEvent.PrivacyPolicyCheckBoxClicked(it))
                    }, errorMsg = registerViewModel.registerUIState.value.privacyPolicyAcceptedMsgError
                )

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.register), onButtonClicked = {
                        registerViewModel.onEvent(RegisterUIEvent.SignUpButtonClicked)
                        if (registerViewModel.registerUIState.value.errorMsg != null) {
                            Toast.makeText(
                                context,
                                registerViewModel.registerUIState.value.errorMsg.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }, true
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(
                    value = stringResource(id = R.string.already_login),
                    onTextSelected = {
                        navController.navigate(Screen.LoginScreen.toString())
                    })
            }
        }

        if (registerViewModel.registerInProgress.value) {
            CircularProgressIndicator()
        }
    }


}

//@Preview
//@Composable
//fun DefaultPreviewOfSignUpScreen() {
//    RegisterScreen()
//}