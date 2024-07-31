package com.miftah.nutrigrade.ui.detail_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    sate : DetailState,

) {
    Column {
        Text(text = "Detail Screen")
        Button(onClick = {

        }) {

        }
    }
}