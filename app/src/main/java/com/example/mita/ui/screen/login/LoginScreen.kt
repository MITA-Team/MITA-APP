package com.example.mita.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mita.R
import com.example.mita.navigation.Screen
import com.example.mita.ui.theme.poppinsFont
import com.example.mita.viewModel.AuthViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    navController: NavController

    ) {
    val colorResId = R.color.orenBackground // Ganti dengan ID warna yang diinginkan
    val backgroundColor = Color(ContextCompat.getColor(LocalContext.current, colorResId))

    var identifier by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // State untuk menampilkan pesan ke pengguna
    var loginResult by remember { mutableStateOf<Boolean?>(null) }

    val loginResultState by authViewModel.loginResult.collectAsState(initial = false)

    // Memperbarui nilai state saat ada perubahan pada LiveData
    loginResult = loginResultState

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Login",
                style = TextStyle(
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                ),
                modifier = Modifier.padding(bottom = 16.dp) // Atur padding di bagian bawah judul
            )
            Image(
                painter = painterResource(id = R.drawable.ic_mita_no_bg),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(150.dp)
                    .padding(bottom = 16.dp)
            )
            TextField(
                value = identifier,
                onValueChange = { identifier = it },
                label = {
                    Text(
                        "Username or Email",
                        fontFamily = poppinsFont,
                        fontWeight = FontWeight.Normal,
                        )
                        },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White
                )
            )

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(
                    "Password",
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.Normal,
                ) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White
                )
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
                    .padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    colorResource(id = R.color.orenButton)
                ),
            ) {
                Text(
                    "Login",
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.SemiBold,
                    )
            }


            // Tampilkan pesan berdasarkan respons dari server
//        when (val result = authViewModel.loginResult.collectAsState().value) {
//            true -> Text("Login Successful!")
//            false -> Text("Login Failed")
//            else -> {}
//        }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreviewOfLoginScreen() {
    val authViewModel = AuthViewModel()
    val navController = rememberNavController()
    LoginScreen(authViewModel, navController)
}