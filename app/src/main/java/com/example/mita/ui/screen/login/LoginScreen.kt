package com.example.mita.ui.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mita.navigation.Screen
import com.example.mita.viewModel.AuthViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    navController: NavController

    ) {

    var identifier by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // State untuk menampilkan pesan ke pengguna
    var loginResult by remember { mutableStateOf<Boolean?>(null) }

    val loginResultState by authViewModel.loginResult.collectAsState(initial = false)

    // Memperbarui nilai state saat ada perubahan pada LiveData
    loginResult = loginResultState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = identifier,
            onValueChange = { identifier = it },
            label = { Text("Username or Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            visualTransformation = PasswordVisualTransformation()
        )

        Button(
            onClick = {
                // Panggil fungsi login dari ViewModel
                val coroutineScope = CoroutineScope(Dispatchers.Main)
                coroutineScope.launch {
                    val result = authViewModel.login(identifier, password)
                    loginResult = result

                    if (result) navController.navigate(Screen.HomeScreen.toString())
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Login")
        }


        // Tampilkan pesan berdasarkan respons dari server
        when (val result = authViewModel.loginResult.collectAsState().value) {
            true -> Text("Login Successful!")
            false -> Text("Login Failed")
            else -> {}
        }
    }
}


//@Preview
//@Composable
//fun DefaultPreviewOfLoginScreen() {
//    val authViewModel = AuthViewModel()
//    val navController = NavController
//    LoginScreen(authViewModel, navController)
//}