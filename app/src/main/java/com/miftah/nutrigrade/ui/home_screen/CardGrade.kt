package com.miftah.nutrigrade.ui.home_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.miftah.nutrigrade.ui.theme.CardRedBg
import com.miftah.nutrigrade.ui.theme.CardRedContent
import com.miftah.nutrigrade.ui.theme.GradeABg
import com.miftah.nutrigrade.ui.theme.GradeAText


@Composable
fun CardGrade(
    bgColor : Color  = GradeABg,
    contentColor : Color = GradeAText,
    value : String
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xff9ACAB4),
            contentColor = Color(0xff0A844C),
        )
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center
        ){
            Text("A", fontSize = 32.sp)
        }
    }
}