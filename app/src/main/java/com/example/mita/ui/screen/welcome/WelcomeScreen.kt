package com.example.mita.ui.screen.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mita.R

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier.fillMaxSize()
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            //Spacer(modifier = Modifier.weight(1f))
            Text(
                "Welcome To",
                fontSize = 32.sp,
            )

            Spacer(modifier = Modifier.padding(8.dp))

            Image(painter = painterResource(id = R.drawable.logo_mita),
                contentDescription = null,
                modifier = Modifier
                    .height(320.dp)
            )

            Spacer(modifier = Modifier.padding(8.dp))

            Row {
                Button(onClick = { /*TODO*/ },
                    shape = MaterialTheme.shapes.large,
                    colors = ButtonDefaults.buttonColors(),
                    modifier = Modifier
                        .padding(8.dp)
                ) {

                    Text(
                        "Sign In"
                    )

                }

                Button(onClick = { /*TODO*/ },
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
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview(){
    WelcomeScreen()
}