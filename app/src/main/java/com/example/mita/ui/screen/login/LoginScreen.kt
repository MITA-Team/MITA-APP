package com.example.mita.ui.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mita.R
import com.example.mita.navigation.Screen

@Composable
fun LoginScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
){

    val edit = remember{ mutableStateOf("") }

    Box(modifier = modifier.fillMaxSize()){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Text(
                stringResource(id = R.string.login),
                style = TextStyle(
                    fontSize = 28.sp,
                    fontFamily = null,
                    fontWeight = FontWeight(500)
                )
            )

            TextField(
                value = edit.value,
                onValueChange = { edit.value = it },
                label = {
                        Text(
                            text = stringResource(id = R.string.emailText)
                        )
                },
                singleLine = true,
                leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                )
            )

            TextField(
                value ="",
                onValueChange = {  },
                label = {
                    Text(
                        text = stringResource(id = R.string.passwordText)
                    )
                },
                singleLine = true,
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "") },
                trailingIcon = { Icon(painter = painterResource(id = R.drawable.baseline_eye_24), contentDescription = "")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
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
                    stringResource(id = R.string.login)
                )

            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun LoginScreenPreview(){
//    LoginScreen()
//}