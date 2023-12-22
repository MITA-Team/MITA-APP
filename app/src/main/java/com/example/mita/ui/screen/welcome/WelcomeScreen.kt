package com.example.mita.ui.screen.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mita.R
import com.example.mita.navigation.Screen
import com.example.mita.ui.theme.poppinsFont

@Composable
fun WelcomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,

){
    val colorResId = R.color.orenBackground // Ganti dengan ID warna yang diinginkan
    val backgroundColor = Color(ContextCompat.getColor(LocalContext.current, colorResId))

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = backgroundColor)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            

            Image(
                painter = painterResource(id = R.drawable.ic_mita_no_bg),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(200.dp)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Text(
                "Welcome To",
                fontSize = 32.sp,
                fontFamily = poppinsFont,
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.padding(8.dp))

            Text(
                "MITA APP",
                fontSize = 32.sp,
                fontFamily = poppinsFont,
                fontWeight = FontWeight.Bold
            )

            /* Image(painter = painterResource(id = R.drawable.logo_mita),
                contentDescription = null,
                modifier = Modifier
                    .height(320.dp)
            )*/

            Spacer(modifier = Modifier.padding(8.dp))

            Row {
                Button(
                    onClick = {
                        navController.navigate(Screen.LoginScreen.toString())
                    },
                    shape = MaterialTheme.shapes.large,
                    colors = ButtonDefaults.buttonColors(
                        colorResource(id = R.color.orenButton)
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                ) {

                    Text(
                        "Log In",
                        color = Color.White, // Warna teks
                        fontFamily = poppinsFont,
                        fontWeight = FontWeight.SemiBold
                    )

                }

                Button(
                    onClick = {
                        navController.navigate(Screen.RegisterScreen.toString())
                    },
                    shape = MaterialTheme.shapes.large,
                    colors = ButtonDefaults.buttonColors(
                        colorResource(id = R.color.orenButton)
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                ) {

                    Text(
                        "Sign Up",
                        color = Color.White, // Warna teks
                        fontFamily = poppinsFont,
                        fontWeight = FontWeight.SemiBold
                    )

                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview(){
    WelcomeScreen(navController = rememberNavController())
}