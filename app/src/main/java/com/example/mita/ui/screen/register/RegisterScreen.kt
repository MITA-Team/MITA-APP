package com.example.mita.ui.screen.register

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mita.navigation.Screen

@Composable
fun RegisterScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    Box(modifier = modifier.fillMaxSize()){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Text(
                "Sign Up",
                style = TextStyle(
                    fontSize = 28.sp,
                    fontFamily = null,
                    fontWeight = FontWeight(500),
                ),
                modifier = Modifier
                    .padding(bottom = 16.dp)
            )

            TextField(
                value = "",
                onValueChange = {},
                label = {
                    Text(
                        text = "Alamat Email"
                    )
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                )
            )

            TextField(
                value = "",
                onValueChange = {},
                label = {
                    Text(
                        text = "Nama Pengguna"
                    )
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                )
            )

            TextField(
                value = "",
                onValueChange = {},
                label = {
                    Text(
                        text = "Kata Sandi"
                    )
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                )
            )

            TextField(
                value = "",
                onValueChange = {},
                label = {
                    Text(
                        text = "Konfirmasi Kata Sandi"
                    )
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                )
            )

            TextField(
                value = "",
                onValueChange = {},
                label = {
                    Text(
                        text = "Tanggal Lahir"
                    )
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                )
            )

            TextField(
                value = "",
                onValueChange = {},
                label = {
                    Text(
                        text = "Domisili"
                    )
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                )
            )

            Button(onClick = { navController.navigate(Screen.Home.route) },
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.buttonColors(),
                modifier = Modifier
                    .padding(8.dp)
            ) {

                Text(
                    "Sign Up"
                )

            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun RegisterScreenPreview(){
//    RegisterScreen()
//}