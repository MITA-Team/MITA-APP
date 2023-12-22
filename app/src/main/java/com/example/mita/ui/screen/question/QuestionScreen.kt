package com.example.mita.ui.screen.question

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mita.R
import com.example.mita.data.response.ListItem
import com.example.mita.navigation.Screen
import com.example.mita.ui.theme.poppinsFont
import com.example.mita.viewModel.QuestionViewModel
import kotlinx.coroutines.launch

@Composable
fun QuestionScreen(questionViewModel: QuestionViewModel = viewModel(),  navController: NavController) {
    val questions = questionViewModel.questions.value
    val currentQuestionIndex = questionViewModel.currentQuestionIndex.value

    val colorResId = R.color.orenBackground // Ganti dengan ID warna yang diinginkan
    val backgroundColor = Color(ContextCompat.getColor(LocalContext.current, colorResId))

    val viewModelScope = rememberCoroutineScope()


    LaunchedEffect(questionViewModel.submitResult.value) {
        val submitResult = questionViewModel.submitResult.value
        if (submitResult != null) {
            // Hasil sudah tersedia, navigasi ke layar hasil
            navController.navigate(Screen.ResultScreen.toString())
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Tampilkan judul pertanyaan
            Text(text = "Questions", fontFamily = poppinsFont, fontSize = 20.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(16.dp))

            // Tampilkan pertanyaan dalam Card
            QuestionItem(
                question = questions.getOrNull(currentQuestionIndex),
                onAnswerSelected = { isYes ->
                    // Use viewModelScope to launch a coroutine
                    viewModelScope.launch {
                        questionViewModel.submitAnswer(isYes)
                    }
                },
                onFinished = {
                    val questionsSize = questions.size
                    if (currentQuestionIndex < questionsSize - 1) {
                        questionViewModel.nextQuestion()
                    } else {
                        navController.navigate(Screen.ResultScreen.toString()) // Navigasi ke ResultScreen
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

        }
    }
}

@Composable
fun QuestionItem(question: ListItem?, onAnswerSelected: (Boolean) -> Unit, onFinished: () -> Unit) {


    val orenButtonColor = LocalContext.current.resources.getColor(R.color.orenButton, null)
    // Tampilkan informasi pertanyaan atau pesan jika tidak ada pertanyaan
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            if (question != null) {
                Text(text = question.data.question, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(16.dp))

            } else {
                Text(
                    text = "No question available.",
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.Normal
                )
            }

        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Column {
        Button(
            onClick = {
                onAnswerSelected(true)
                onFinished()
                      },
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(
                colorResource(id = R.color.orenButton)
            ),
            content = {
                Text(
                    text = "Ya",
                    color = Color.White, // Warna teks
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.SemiBold
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onAnswerSelected(false)
                      onFinished()},
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(
                colorResource(id = R.color.orenButton)
            ),
        ) {
            Text(
                text = "Tidak",
                color = Color.White, // Warna teks
                fontFamily = poppinsFont,
                fontWeight = FontWeight.SemiBold
                )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))



    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Button(
            onClick = { onFinished() },
            modifier = Modifier
                .height(40.dp)
                .widthIn(100.dp),
            colors = ButtonDefaults.buttonColors(
                colorResource(id = R.color.orenButton)
            ),
            content = {
                Text(
                    text = "Selesai",
                    color = Color.White, // Warna teks
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.SemiBold
                )
            }
        )
    }

}




@Preview(showBackground = true)
@Composable
fun preview(){
    QuestionScreen(navController = rememberNavController())
}
