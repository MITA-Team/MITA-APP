package com.example.mita.ui.screen.result

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun ResultScreen(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            // Main Card
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                modifier = Modifier
                    .size(width = 360.dp, height = 150.dp)
            ) {

                Row {
                    Text(
                        text = "Statistik Hasil",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(8.dp)
                    )
                    Text(
                        text = "1",
                        fontSize = 32.sp,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = "/ 30",
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .align(Alignment.CenterVertically)
                    )

                    //Right Side
                    //Inner Card
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ){
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            modifier = Modifier
                                .size(width = 120.dp, height = 60.dp)
                                .align(Alignment.End)
                        ) {
                            Text(
                                text = "Hasil",
                                modifier = Modifier
                                    .padding(8.dp)
                                    .align(Alignment.CenterHorizontally),
                                textAlign = TextAlign.Center
                            )
                        }

                        Spacer(modifier = Modifier.padding(8.dp))
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            modifier = Modifier
                                .size(width = 120.dp, height = 60.dp)
                                .align(Alignment.End)
                        ) {
                            Text(
                                text = "Delay",
                                modifier = Modifier
                                    .padding(8.dp)
                                    .align(Alignment.CenterHorizontally),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.padding(16.dp))

            //Recomend
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .size(width = 360.dp, height = 150.dp)
                    .border(1.dp, Color.Black, shape = RoundedCornerShape(10.dp))
            ) {
                Text(
                    text = "Rekomendasi :",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(8.dp)
                )

                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum",
                    fontSize = 11.sp,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.padding(16.dp))
            //Therapy List
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
fun ResultScreenPreview(){
    ResultScreen()
}