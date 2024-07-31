package com.miftah.nutrigrade.ui.home_screen

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.miftah.nutrigrade.R

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        Modifier.padding(16.dp)
    ) {
        Text("Home")
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            modifier = Modifier.padding(vertical = 12.dp)
        ) {

           item {
               Image(painter = painterResource(id = R.drawable.news_image) , contentDescription = "image",modifier.fillMaxWidth() )
               Spacer(modifier = Modifier.width(16.dp))
               Image(painter = painterResource(id = R.drawable.news_image3) , contentDescription = "image",modifier.fillMaxWidth() )
           }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Riwayat Pemindaian")
        LazyColumn(
           verticalArrangement = Arrangement.spacedBy(16.dp)
       ) {
           item {
               CardBox(
                   "Chitato"
               )
               CardBox(
                   productName = "bembemng")
               CardBox(
                   productName = "jamu"
               )

           }
       }


    }
}