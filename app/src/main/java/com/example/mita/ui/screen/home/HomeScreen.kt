package com.example.mita.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mita.ui.component.ListItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 48.dp)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {

            Card( // Main card
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
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
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Terapi A",
                            modifier = Modifier
                                .align(Alignment.Start)
                        )

                        Spacer(modifier = Modifier.padding(top = 32.dp))

                        Row( // Row with day and therapy progress
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = "Day",
                            )

                            Spacer(modifier = Modifier.padding(24.dp))
                            Text(
                                text = "1",
                                fontSize = 30.sp
                            )

                            Text(
                                text = "/30",
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
            )
            ListItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            ListItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
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
    HomeScreen()
}