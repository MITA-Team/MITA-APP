package com.example.mita.ui.screen.register

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mita.R
import com.example.mita.viewModel.AuthViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    authViewModel: AuthViewModel,
    navController: NavController.Companion
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var domicile by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf(Date()) }
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
            val selectedDate = Calendar.getInstance().apply {
                set(Calendar.YEAR, year)
                set(Calendar.MONTH, month)
                set(Calendar.DAY_OF_MONTH, day)
            }.time
            birthDate = selectedDate
        }, year, month, day
    )

    fun showDataPicker(){
        datePickerDialog.show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        TextField(
            value = domicile,
            onValueChange = { domicile = it },
            label = { Text("Domicile") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Row {
            TextField(
                value = SimpleDateFormat("dd/MM/yyyy").format(birthDate),
                onValueChange = {},
                label = { Text("Birthdate") },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                readOnly = true
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
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            visualTransformation = PasswordVisualTransformation()
        )

        TextField(
            value = confirmPass,
            onValueChange = { confirmPass = it },
            label = { Text("Confirm Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            visualTransformation = PasswordVisualTransformation()
        )

        Button(
            onClick = {
                // Panggil fungsi register dari ViewModel
                authViewModel.register(username, email, domicile, birthDate, password, confirmPass)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Register")
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



@Composable
@Preview(showBackground = true)
fun PreviewRegisterScreen() {
    val authViewModel = AuthViewModel()
    val navController = NavController// Gantilah dengan instance yang sesuai
    RegisterScreen(authViewModel, navController) // Create an instance of your ViewModel
}