package com.example.mita.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mita.R
import com.example.mita.navigation.Screen
import com.example.mita.ui.theme.poppinsFont
import com.example.mita.viewModel.AuthViewModel

@Composable
fun ProfileScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel

) {

    LaunchedEffect(Unit) {
        val loginSuccess = viewModel.login("username", "password") // Ganti dengan credential yang benar
        if (loginSuccess) {
            // Tidak perlu memanggil fetchUserData di sini karena sudah dipanggil di dalam fungsi login
        } else {
            // Handle jika login gagal
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.orenBackground))
    ) {
        item {
            TopBar(modifier, onBackClick)
        }
        item {
            ProfileContent(modifier, navController = rememberNavController(), authViewModel = AuthViewModel())
        }
    }
}



@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(colorResource(R.color.orenButton))
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            tint = Color.White,
            contentDescription = null,
            modifier = Modifier
                .padding(16.dp)
                .clickable { onBackClick() }
        )
        Text(
            text = stringResource(R.string.menu_profile),
            fontSize = 20.sp,
            fontFamily = poppinsFont,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp)
            ) {
                Image(
                    // Gunakan data profil untuk menampilkan gambar atau ambil dari sumber lain jika ada
                    painter = painterResource(id = R.drawable.ballon),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .size(150.dp)
                        .clip(CircleShape)
                )
                Text(
                    text = stringResource(id = R.string.name), // Gunakan informasi profil untuk nama
                    fontSize = 30.sp,
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            Column(
                modifier = modifier
                    .padding(start = 10.dp, end = 10.dp)
                    .fillMaxHeight()
                    .background(Color.White)
            ) {
                BioItem(
                    title = R.string.birthdate,
                    description = R.string.dev_birthdate, // Gunakan informasi profil untuk tanggal lahir
                    modifier = modifier.padding(top = 10.dp)
                )
                BioItem(
                    title = R.string.domisili,
                    description = R.string.asal // Gunakan informasi profil untuk domisili
                )
                BioItem(
                    title = R.string.email,
                    description = R.string.email_contoh // Gunakan informasi profil untuk email
                )
            }
        }
        Button(
            onClick = {
                navController.navigate(Screen.WelcomeScreen.toString())
            },
            shape = MaterialTheme.shapes.large,
            colors = ButtonDefaults.buttonColors(
                colorResource(id = R.color.orenButton)
            ),
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "LogOut",
                fontFamily = poppinsFont,
                fontWeight = FontWeight.SemiBold
            )
        }

}

@Composable
fun BioItem(
    title: Int,
    description: Int,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(3.dp),
        modifier = modifier
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(title),
            fontFamily = poppinsFont,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = colorResource(R.color.orenButton),
        )
        Text(
            text = stringResource(id = description),
            fontFamily = poppinsFont,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        Divider(
            color = colorResource(R.color.orenButton),
            thickness = 2.dp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(onBackClick = {}, viewModel = AuthViewModel())
}