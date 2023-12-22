package com.example.mita.ui.screen.register

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import com.example.mita.R
import com.example.mita.data.response.RegisterRequest
import com.example.mita.ui.theme.poppinsFont
import com.example.mita.viewModel.AuthViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    authViewModel: AuthViewModel,
    navController: NavController.Companion
) {
    val colorResId = R.color.orenBackground // Ganti dengan ID warna yang diinginkan
    val backgroundColor = Color(ContextCompat.getColor(LocalContext.current, colorResId))

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var domicile by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPass by remember { mutableStateOf("") }

    // Variabel untuk menampilkan pesan ke pengguna
    val registrationResult by remember { mutableStateOf<Boolean?>(null) }

    val c = Calendar.getInstance()

    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)

    val context = LocalContext.current

    val icon = painterResource(R.drawable.ic_calendar)

    val datePickerDialog = DatePickerDialog(
        context, { d, year, month, day ->
            val bulan = month + 1
            birthDate = "$day - $month - $year"
        }, year, month, day
    )

    fun showDataPicker(){
        datePickerDialog.show()
    }

    val registrationStatus by authViewModel.registrationResult.observeAsState()


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
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                "Register",
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
                    .size(100.dp)
                    .padding(bottom = 16.dp)
            )
            TextField(
                value = username,
                onValueChange = { username = it },
                label = {
                    Text(
                        "Username",
                        fontFamily = poppinsFont,
                        fontWeight = FontWeight.Normal
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
                value = email,
                onValueChange = { email = it },
                label = { Text(
                        "Email",
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.Normal
                ) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White
                )
            )

            TextField(
                value = domicile,
                onValueChange = { domicile = it },
                label = { Text(
                    "Domicile",
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.Normal
                ) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White
                )
            )

            Row {
                TextField(
                    value = birthDate,
                    onValueChange = { birthDate = it },
                    label = { Text(
                        "Birthdate",
                        fontFamily = poppinsFont,
                        fontWeight = FontWeight.Normal
                    ) },
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    readOnly = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White
                    )
                )
                IconButton(
                    onClick = { datePickerDialog.show() },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(
                        icon,
                        contentDescription = "Open calendar picker",
                        modifier = Modifier.size(24.dp)
                    )

                }
            }


            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(
                    "Password",
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.Normal
                ) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White
                )
            )

            TextField(
                value = confirmPass,
                onValueChange = { confirmPass = it },
                label = { Text(
                    "Confirm Password",
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.Normal
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
                    val registerRequest = RegisterRequest(
                        username = username,
                        email = email,
                        domicile = domicile,
                        birthDate = birthDate,
                        password = password,
                        confirmPass = confirmPass
                    )
                    // Panggil fungsi register dari ViewModel
                    authViewModel.register(registerRequest)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    colorResource(id = R.color.orenButton)
                ),
            ) {
                Text(
                    "Register",
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.SemiBold
                )
            }

            // Tampilkan pesan berdasarkan respons dari server
            registrationResult?.let { result ->
                if (result) {
                    Text("Registration Successful!")
                } else {
                    Text("Registration Failed")
                }
            }
        }
    }
}



@Composable
@Preview(showBackground = true)
fun PreviewRegisterScreen() {
    val authViewModel = AuthViewModel()
    val navController = NavController// Gantilah dengan instance yang sesuai
    RegisterScreen(authViewModel, navController) // Create an instance of your ViewModel
}