package com.miftah.nutrigrade.ui.detail_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.miftah.nutrigrade.R
import com.miftah.nutrigrade.ui.home_screen.CardGrade
import com.miftah.nutrigrade.ui.navgraph.Route
import com.miftah.nutrigrade.ui.theme.CarboBg
import com.miftah.nutrigrade.ui.theme.GaramBg
import com.miftah.nutrigrade.ui.theme.GreenPrimary
import com.miftah.nutrigrade.ui.theme.GulaBg
import com.miftah.nutrigrade.ui.theme.LemakBg
import com.miftah.nutrigrade.ui.theme.NutriGradeTheme
import com.miftah.nutrigrade.ui.theme.ProteinBg
import com.miftah.nutrigrade.ui.theme.SeratBg

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    sate: DetailState = DetailState(),
    onEvent: (DetailEvent) -> Unit
) {
    Column {
        Column {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)) {
                Image(
                    modifier = Modifier
                        .height(170.dp)
                        .fillMaxWidth(),
                    painter = painterResource(id = R.drawable.sampel),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .clip(CircleShape)
                        .background(Color.Black),
                    onClick = {  },
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }

        }
        Column(
            modifier  = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier  = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text("Good Day" , fontWeight = FontWeight.SemiBold , fontSize = 18.sp)
                Text("20g")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row {

            }
        }
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Surface {
                Column(
                    modifier = Modifier.height(IntrinsicSize.Max)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Informasi Nutrisi",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
//                        CircularProgressIndicator(
//                            modifier = Modifier.width(60.dp),
//                            color = MaterialTheme.colorScheme.secondary,
//                            trackColor = MaterialTheme.colorScheme.surfaceVariant,
//                        )
                        CardGrade(value = "A")
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            modifier = Modifier.fillMaxHeight(),
                            text = "Kurang sehat,batasi konsums Produk in lebih tinggi kalori gula,garam atau lemak jenuh dan sebaiknya dikonsumsi secara terbatas"
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(
                        RoundedCornerShape(8.dp)
                    )
                    .background(GreenPrimary)

                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Nutrisi dalam 50g", color= Color.White)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(GreenPrimary)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Nutrisi dalam 100g" ,  color= Color.White)
            }
        }
        Row {
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Lemak")
                    CustomLinearProgressIndicator(
                        progressColor = LemakBg,
                        progress = 1f,
                        modifier = Modifier,

                        )
                }
                Column {
                    Text(text = "Karbo")
                    CustomLinearProgressIndicator(
                        progressColor = CarboBg,
                        progress = 0.5f
                    )
                }
                Column {
                    Text(text = "Serat")
                    CustomLinearProgressIndicator(
                        progress = 0.5f,
                        progressColor = SeratBg,


                        )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Column {
                    Text(text = "Protein")
                    CustomLinearProgressIndicator(
                        progress = 0.5f,
                        progressColor = ProteinBg
                    )
                }
                Column {
                    Text(text = "Garam")
                    CustomLinearProgressIndicator(
                        progress = 0.5f,
                        progressColor = GaramBg

                    )
                }
                Column {
                    Text(text = "Gula")
                    CustomLinearProgressIndicator(
                        progressColor = GulaBg,
                        progress = 0.5f,

                    )
                }
            }
        }
    }


//    Spacer(modifier = Modifier.height(16.dp))

//    Spacer(modifier = Modifier.height(16.dp))

}

@Composable
fun CustomLinearProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float,
    progressColor: Color = Color.Green,
    backgroundColor: Color = Color.Black,
    clipShape: Shape = RoundedCornerShape(16.dp)
) {
    Box(
        modifier = modifier
            .clip(clipShape)
            .background(backgroundColor)
            .height(8.dp)
    ) {
        Box(
            modifier = Modifier
                .background(progressColor)
                .fillMaxHeight()
                .fillMaxWidth(progress)
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    NutriGradeTheme {
        DetailScreen(){

        }
    }
}