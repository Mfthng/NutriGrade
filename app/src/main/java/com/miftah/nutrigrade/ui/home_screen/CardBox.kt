package com.miftah.nutrigrade.ui.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.miftah.nutrigrade.R
import com.miftah.nutrigrade.ui.theme.CardRedBg
import com.miftah.nutrigrade.ui.theme.CardRedContent
import com.miftah.nutrigrade.ui.theme.NutriGradeTheme

@Composable
fun CardBox(
    productName : String,
    gradeResult : String = "A",
) {
   Card(
       colors =  CardDefaults.cardColors(
           containerColor = Color(0xFFFFFFFF)
       ),
       modifier = Modifier
           .fillMaxWidth()
           .padding(vertical = 4.dp)
           .height(IntrinsicSize.Max)
   ){
       Row(
           verticalAlignment = Alignment.CenterVertically,
           modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
       ){
           AsyncImage(model = "https://asset-a.grid.id/crop/0x0:0x0/700x465/photo/2020/08/07/3243144509.jpeg",
               contentScale = ContentScale.Crop,
               contentDescription = "Card Image" , modifier = Modifier
                   .size(60.dp)
                   .clip(RoundedCornerShape(12.dp))
           )
          Row(
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.fillMaxWidth()
          ){
              Column(
                  verticalArrangement = Arrangement.SpaceBetween,

                  modifier = Modifier
                      .padding(12.dp))

              {
                  Text(productName , fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                  Spacer(modifier = Modifier.height(8.dp))

                  Row(
                      modifier = Modifier.height(IntrinsicSize.Max)
                  ) {
                      CardWarning(value =  "Tinggi Lemak" , contentColor = CardRedContent, bgColor = CardRedBg )
                  }
              }

             CardGrade(value = gradeResult )
          }
       }
   }
}

@Preview
@Composable
private fun CardBoxPreview() {
    NutriGradeTheme {
        CardBox(productName = "Chitato", gradeResult = "A")
    }
}