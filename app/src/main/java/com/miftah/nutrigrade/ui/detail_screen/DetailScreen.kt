package com.miftah.nutrigrade.ui.detail_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.miftah.nutrigrade.R
import com.miftah.nutrigrade.domain.Scanned
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
    state: DetailState = DetailState(),
    onEvent: (DetailEvent) -> Unit,
    navigate: () -> Unit
) {
    Scaffold(
        bottomBar = {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                onClick = {
                    onEvent(DetailEvent.SaveTODB(state.scanned!!))
                    navigate()
                }) {
                Text(text = "Save To DB")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Max)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(state.scanned!!.productPhoto)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(170.dp)
                    )
                    IconButton(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .clip(CircleShape)
                            .background(Color.Black),
                        onClick = { },
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        state.scanned!!.productName,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                    Text(state.scanned.portionSize.toString() + " g")
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
                            val grade = gradeGenerator(state.scanned!!.grade)
                            CardGrade(value = grade.grade)
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                modifier = Modifier.fillMaxHeight(),
                                text = grade.text
                            )
                        }
                    }
                }
            }
            /*Row(
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
                    Text(text = "Nutrisi dalam 50g", color = Color.White)
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
                    Text(text = "Nutrisi dalam 100g", color = Color.White)
                }
            }*/
            Row(
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Lemak")
                            Text(text = state.scanned!!.cholesterol.toString() + " g")
                        }
                        CustomLinearProgressIndicator(
                            progressColor = LemakBg,
                            progress = 1F,
                            modifier = Modifier,

                            )
                    }
                    Column {
                        Row(
                            modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Karbo")
                            Text(text = state.scanned!!.totalCarbs.toString() + " g")
                        }
                        CustomLinearProgressIndicator(
                            progressColor = CarboBg,
                            progress = 1f
                        )
                    }
                    Column {
                        Row(
                            modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Serat")
                            Text(text = state.scanned!!.dietaryFiber.toString() + " g")
                        }
                        CustomLinearProgressIndicator(
                            progress = 1f,
                            progressColor = SeratBg,


                            )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Column {
                        Row(
                            modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Protein")
                            Text(text = state.scanned!!.protein.toString() + " g")
                        }
                        CustomLinearProgressIndicator(
                            progress = 1f,
                            progressColor = ProteinBg
                        )
                    }

                    Column {
                        Row(
                            modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Garam")
                            Text(text = state.scanned!!.sodium.toString() + " mg")
                        }
                        CustomLinearProgressIndicator(
                            progress = 1f,
                            progressColor = GaramBg

                        )
                    }
                    Column {
                        Row(
                            modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Gula")
                            Text(text = state.scanned!!.sugars.toString() + " g")
                        }
                        CustomLinearProgressIndicator(
                            progressColor = GulaBg,
                            progress = 1f,
                        )
                    }
                }
            }
        }

    }
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

data class GradeClass(
    val grade: String,
    val text: String
)

fun gradeGenerator(grade: String): GradeClass {
    return when (grade) {
        "A" -> GradeClass(
            grade = "A",
            text = "Sehat, pilihan baik untuk dikonsumsi karena produk ini memiliki protein yang tinggi atau memiliki sedikit kalori, gula, garam atau lemak."
        )

        "B" -> GradeClass(
            grade = "B",
            text = "Sehat, pilihan baik untuk dikonsumsi. Produk ini memiliki sedikit lebih banyak kalori, gula, garam, atau lemak jenuh, tetapi masih merupakan pilihan yang sehat."
        )

        "C" -> GradeClass(
            grade = "C",
            text = "Sehat, pilihan baik untuk dikonsumsi. Produk ini memiliki sedikit lebih banyak kalori, gula, garam, atau lemak jenuh, perlu pengawasan dan batasan konsumsi."
        )

        "D" -> GradeClass(
            grade = "D",
            text = "Kurang Sehat, batasi konsumsi, produk ini lebih tinggi kalori, gula, garam, atau lemak jenuh dan sebaiknya dikonsumsi secara terbatas."
        )

        "E" -> GradeClass(
            grade = "E",
            text = "Kurang Sehat, batasi konsumsi, produk ini lebih tinggi kalori, gula, garam, atau lemak jenuh dan sebaiknya dikonsumsi secara terbatas."
        )

        else -> GradeClass(
            grade = "C",
            text = "Kurang Sehat, batasi konsumsi, produk ini lebih tinggi kalori, gula, garam, atau lemak jenuh dan sebaiknya dikonsumsi secara terbatas."
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    NutriGradeTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            DetailScreen(
                onEvent = {

                }
            ) {

            }
        }

    }
}