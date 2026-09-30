package com.company.jetpackbottombarexample.main.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeDetailOne(modifier: Modifier = Modifier, name: String,onBack: () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black.copy(0.1f)),
            title = {
                Text(text = "HomeDetailOne Screen", fontSize = 18.sp)
            }, navigationIcon = {
                IconButton(onClick = {
                    onBack()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back",
                        modifier = Modifier.padding(end = 12.dp)
                    )
                }

            })
    }) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Key : $name",
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
            Text(
                text = "HomeDetailOne Screen ",
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
        }
    }

}