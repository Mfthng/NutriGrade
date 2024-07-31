package com.miftah.nutrigrade.ui.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.miftah.nutrigrade.R

@Composable
fun CardBox() {
   Card(
       colors =  CardDefaults.cardColors(
           containerColor = Color(0xFFFFFFFF)
       ),
       modifier = Modifier.fillMaxWidth().height(66.dp)
   ){
       Row(
           modifier =  Modifier.padding(12.dp)
       ){
           Image(painter = painterResource(id = R.drawable.sampel), contentDescription = "Card Image" , modifier = Modifier.width(50.dp).height(50.dp) )
          Row(
              horizontalArrangement = Arrangement.SpaceBetween,
              modifier = Modifier.fillMaxWidth()
          ){
              Column(
                  verticalArrangement = Arrangement.SpaceBetween,
                  modifier = Modifier
                      .padding(12.dp))
              {
                  Text("Chitato")
              }
              Card(
                  colors =   CardDefaults. cardColors(
                      containerColor = Color(0xff9ACAB4),
                      contentColor = Color(0xff0A844C)
                  ),
                  modifier = Modifier.fillMaxHeight().padding(16.dp)
              ) {
                  Text("A")
              }
          }
       }
   }
}