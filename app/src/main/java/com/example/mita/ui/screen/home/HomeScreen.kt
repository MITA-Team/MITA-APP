package com.example.mita.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mita.R
import com.example.mita.navigation.Screen
import com.example.mita.ui.component.ListItem
import com.example.mita.ui.theme.poppinsFont

@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
){

    val colorResId = R.color.orenBackground // Ganti dengan ID warna yang diinginkan
    val backgroundColor = Color(ContextCompat.getColor(LocalContext.current, colorResId))
    val orenButtonColor = colorResource(id = R.color.orenButton)

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 48.dp)
            .background(backgroundColor)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {

            Card( // Main card
                colors = CardDefaults.cardColors(
                    containerColor = orenButtonColor,
                ),
                modifier = Modifier
                    .size(width = 360.dp, height = 150.dp)
            ) {

                Row( // Row inside the main card
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 16.dp, top = 16.dp)
                ) {

                    Card( // Inner card
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                        ),
                        modifier = Modifier
                            .size(width = 120.dp, height = 120.dp)
                    ) {
                        Text(
                            text = "Hasil Test",
                            fontFamily = poppinsFont,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.CenterHorizontally),
                            textAlign = TextAlign.Center,
                        )
                    }

                    Spacer(modifier = Modifier.padding(16.dp))

                    Column( // Column with therapy details
                        modifier = Modifier
                            .padding(8.dp)
                    ) {

                        Text(
                            text = "Terapi yang di jalani",
                            fontFamily = poppinsFont,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Terapi A",
                            fontFamily = poppinsFont,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                                .align(Alignment.Start)
                        )

                        Spacer(modifier = Modifier.padding(top = 32.dp))

                        Row( // Row with day and therapy progress
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = "Day",
                                fontFamily = poppinsFont,
                                fontWeight = FontWeight.Normal,
                            )

                            Spacer(modifier = Modifier.padding(24.dp))
                            Text(
                                text = "1",
                                fontFamily = poppinsFont,
                                fontWeight = FontWeight.Normal,
                                fontSize = 30.sp
                            )

                            Text(
                                text = "/30",
                                fontFamily = poppinsFont,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier
                                    .align(Alignment.Bottom)
                                    .padding(bottom = 16.dp)
                            )
                        }

                    }
                }
            }
            // **Add the ListItem component here**
            ListItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        navController.navigate(Screen.QuestionScreen.toString())
                    },

            )
            ListItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }

    }

}



@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(navController = rememberNavController())
}