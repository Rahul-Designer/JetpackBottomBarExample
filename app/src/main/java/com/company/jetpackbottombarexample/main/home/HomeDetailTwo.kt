package com.company.jetpackbottombarexample.main.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeDetailTwo(modifier: Modifier = Modifier, name: String) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Key : $name",
            fontSize = MaterialTheme.typography.titleLarge.fontSize
        )
        Text(
            text = "HomeDetailTwo Screen ",
            fontSize = MaterialTheme.typography.titleLarge.fontSize
        )
    }
}