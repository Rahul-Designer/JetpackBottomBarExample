package com.company.jetpackbottombarexample.auth.authScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.company.jetpackbottombarexample.auth.viewModel.SplashViewModel
import com.company.jetpackbottombarexample.ui.theme.JetpackBottomBarExampleTheme


@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    val viewModel: SplashViewModel = hiltViewModel()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.size(30.dp))
        Spacer(Modifier.size(16.dp))
        Text("Splash Screen", fontSize = 18.sp)
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    JetpackBottomBarExampleTheme {
        SplashScreen()
    }
}