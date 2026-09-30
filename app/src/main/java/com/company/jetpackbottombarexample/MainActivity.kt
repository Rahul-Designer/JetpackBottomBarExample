package com.company.jetpackbottombarexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.company.jetpackbottombarexample.auth.navigation.RootGraph
import com.company.jetpackbottombarexample.ui.theme.JetpackBottomBarExampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackBottomBarExampleTheme {
                RootGraph()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    JetpackBottomBarExampleTheme {
        RootGraph()
    }
}