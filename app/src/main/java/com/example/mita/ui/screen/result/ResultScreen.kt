package com.example.mita.ui.screen.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.mita.R
import com.example.mita.navigation.Screen
import com.example.mita.ui.component.ListItem
import com.example.mita.ui.theme.poppinsFont
import com.example.mita.viewModel.QuestionViewModel

@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    questionViewModel: QuestionViewModel = viewModel()
){

    val answeredQuestions = questionViewModel.answeredQuestions.observeAsState(emptyMap())

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
                    containerColor = colorResource(id = R.color.orenButton)
                ),
                modifier = Modifier
                    .size(width = 360.dp, height = 150.dp)
            ) {

                Row {
                    Text(
                        text = "Statistik Hasil",
                        fontFamily = poppinsFont,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(8.dp)
                    )
                    Text(
                        text = "4",
                        fontSize = 32.sp,
                        fontFamily = poppinsFont,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = "/ 15",
                        fontFamily = poppinsFont,
                        fontWeight = FontWeight.Normal,
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
                                .size(width = 120.dp, height = 100.dp)
                                .align(Alignment.End)
                        ) {
                            Text(
                                text = "Hasil",
                                fontFamily = poppinsFont,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .align(Alignment.CenterHorizontally),
                                textAlign = TextAlign.Center
                            )


                            Text(
                                text = "Delay Social",
                                modifier = Modifier
                                    .padding(4.dp)
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
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(8.dp)
                )

                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum",
                    fontSize = 11.sp,
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }

            Spacer(modifier = Modifier.padding(16.dp))
            //Therapy List
            ListItemResult(
               text = "Teraphy",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                navController = navController
            )

        }
    }
}

@Composable
fun ListItemResult(
    text: String,
    modifier: Modifier,
    navController: NavHostController
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable {
                           navController.navigate(Screen.ActivityScreen.toString())
                },
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = R.drawable.terapi,
                contentDescription = "banner",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(84.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth()
            ) {

                Text(
                    text = "Terapi",
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    color = MaterialTheme.colorScheme.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    )

                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Kami merekomendasikan untuk mengikuti terapi ini untuk anak anda",
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.Normal,
                    maxLines = 2,
                    color = MaterialTheme.colorScheme.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium

                )

            }

        }
    }
}




@Preview(showBackground = true)
@Composable
fun ResultScreenPreview(){
    ResultScreen(navController = rememberNavController())
}