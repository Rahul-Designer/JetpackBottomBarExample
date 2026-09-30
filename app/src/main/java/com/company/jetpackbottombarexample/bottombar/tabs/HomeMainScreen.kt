package com.company.jetpackbottombarexample.bottombar.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
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
fun HomeMainScreen(
    modifier: Modifier = Modifier,
    openInHomeDetailOne: () -> Unit,
    openInHomeDetailTwo: () -> Unit,
    openInHomeDetailSeparate: (id: String) -> Unit,
    openBookingFlow: () -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black.copy(0.1f)),
            title = {
                Text(text = "Home Screen", fontSize = 18.sp)
            })
    }) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 12.dp)
                .padding(top = 12.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            )
            {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .height(150.dp)
                        .background(Color.Blue, RoundedCornerShape(10.dp))
                        .clickable {
                            // open in HomeDetailOne Screen
                            openInHomeDetailOne()
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Card One")
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .height(150.dp)
                        .background(Color.Red, RoundedCornerShape(10.dp))
                        .clickable {
                            // open in HomeDetail Screen
                            openInHomeDetailTwo()
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Card Two")
                }

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            )
            {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .height(150.dp)
                        .background(Color.Red, RoundedCornerShape(10.dp))
                        .clickable {
                            // open in HomeDetailThird Screen which separate to HomeMainScreen
                            openInHomeDetailSeparate("3")
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Card Third")
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .height(150.dp)
                        .background(Color.Blue, RoundedCornerShape(10.dp))
                        .clickable {
                            // open in BookingFlow
                            openBookingFlow()
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Card Fourth")
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            )
            {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .height(150.dp)
                        .background(Color.Blue, RoundedCornerShape(10.dp))
                        .clickable {
                            // open in HomeDetailOne Screen
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Card One")
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .height(150.dp)
                        .background(Color.Red, RoundedCornerShape(10.dp))
                        .clickable {
                            // open in HomeDetailTwo Screen
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Card Two")
                }

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .height(150.dp)
                        .background(Color.Red, RoundedCornerShape(10.dp))
                        .clickable {
                            // open in HomeDetailTwo Screen
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Card One")
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .height(150.dp)
                        .background(Color.Blue, RoundedCornerShape(10.dp))
                        .clickable {
                            // open in HomeDetailOne Screen
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Card Two")
                }
            }
            Spacer(modifier = Modifier)

        }
    }

}