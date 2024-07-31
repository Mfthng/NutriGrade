package com.miftah.nutrigrade.ui.home_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miftah.nutrigrade.ui.theme.CardRedBg
import com.miftah.nutrigrade.ui.theme.CardRedContent

@Composable
fun CardWarning(
    bgColor : Color  = CardRedBg ,
    contentColor : Color = CardRedContent,

    value : String
) {
    Card(
        modifier = Modifier.fillMaxHeight(),
        colors = CardDefaults.cardColors(
            containerColor = bgColor,
            contentColor = contentColor
        )
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
        ){
            Text(value, fontSize = 12.sp )
        }
    }
}