package com.example.mita.ui.screen.activity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mita.R
import com.example.mita.navigation.Screen
import com.example.mita.ui.theme.poppinsFont

@Composable
fun ActivityScreen(
    modifier: Modifier = Modifier,
    navController: NavController
){

    val colorResId = R.color.orenBackground // Ganti dengan ID warna yang diinginkan
    val backgroundColor = Color(ContextCompat.getColor(LocalContext.current, colorResId))
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 48.dp)
            .background(backgroundColor)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                ),
                modifier = Modifier
                    .size(width = 360.dp, height = 700.dp)
            ) {

                Text(
                    text = "Activity",
                    fontSize = 24.sp,
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "\n" +
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam faucibus, arcu id mattis consectetur, mauris odio semper enim, id scelerisque tellus purus a mi. Donec vel est ipsum. Sed quam eros, mollis non velit quis, tincidunt semper quam. Pellentesque faucibus, lectus vel luctus laoreet, sapien mauris luctus tortor, et semper ligula massa ut nisl. Phasellus quis nibh ac sem semper tincidunt. Donec sed arcu et justo ullamcorper ultricies. Sed semper, lorem ac vehicula tincidunt, massa eros tincidunt lectus, vel luctus orci mi eget leo.\n\n" +
                            "Fusce orci erat, iaculis nec quam a, semper ultrices quam. Fusce vel diam eget eros dignissim laoreet. Donec euismod, dui id ornare tincidunt, purus tellus tincidunt arcu, ut tincidunt mauris metus a enim. Nulla semper ipsum eget nisl aliquet, sed ultricies purus luctus. Nullam et enim faucibus, consectetur purus sit amet, semper mauris. Proin velit enim, faucibus eget arcu non, sagittis ullamcorper ligula. Fusce ac enim a quam semper tincidunt. Mauris vel quam at ipsum hendrerit suscipit.",
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                )

            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Button(
                    onClick = { navController.navigate(Screen.HomeScreen.toString()) },
                    shape = MaterialTheme.shapes.large,
                    colors = ButtonDefaults.buttonColors(
                        colorResource(id = R.color.orenButton)
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Selesai",
                        fontFamily = poppinsFont,
                        fontWeight = FontWeight.SemiBold,
                        )
                }


            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun show(){
    ActivityScreen(navController = rememberNavController())
}