package com.miftah.nutrigrade.ui.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeState = HomeState(),
    onEvent : (HomeEvent) -> Unit
) {
    Column(
        Modifier.padding(16.dp)
    ) {
        Text("Home")
        Box(){

        }

        Text("Riwayat Pemindaian")
        state.history?.collectAsState(initial = null)?.value.let { data ->
            if(data!= null){
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(items = data, key = {it.id}){
                        CardBox(
                            productName = it.productName,
                            gradeResult = it.grade,
                        )
                    }
                }
            }
        }




    }
}