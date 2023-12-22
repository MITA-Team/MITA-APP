package com.example.mita.ui.screen.therapy

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mita.R
import com.example.mita.data.response.ListItem
import com.example.mita.data.response.Therapy
import com.example.mita.navigation.Screen
import com.example.mita.viewModel.TherapyViewModel

@Composable
fun TherapyScreen(
    therapyViewModel: TherapyViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val therapies = therapyViewModel.therapies.value
    val isLoading = therapyViewModel.isLoading.value
    val error = therapyViewModel.error.value

    LaunchedEffect(true) {
        therapyViewModel.fetchTherapies("YourTherapyString")
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        if (isLoading == true) {
            // Tampilkan indikator loading
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (error != null) {
            // Tampilkan pesan error
            Text(
                text = error,
                color = Color.Red,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn {
                itemsIndexed(therapies?.list ?: emptyList()) { index, therapyItem ->
                    TherapyItem(therapy = therapyItem?.data?.therapy)
                }
            }

//            Button(
//                onClick = {
//                navController.navigate(Screen.HomeScreen.toString())
//            },
//                modifier = Modifier
//                    .padding(8.dp)
//                    .fillMaxWidth()
//            ) {
//                Text(text = "Selesai")
//            }

        }

    }
}

@Composable
fun TherapyItem(therapy: Therapy?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            if (therapy != null) {
                Text(text = therapy.name ?: "", fontSize = 18.sp)
                therapy.description?.forEach { descriptionLine ->
                    Text(text = descriptionLine ?: "", fontSize = 14.sp)
                }
            } else {
                Text(text = "No therapy available.")
            }
        }

    }
}




@Preview(showBackground = true)
@Composable
fun TherapyScreenPreview(){
    TherapyScreen(navController = rememberNavController())
}