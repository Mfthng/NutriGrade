package com.miftah.nutrigrade.ui.onboarding_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import com.google.accompanist.pager.HorizontalPager
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.miftah.nutrigrade.R
import com.miftah.nutrigrade.ui.theme.GreenPrimary

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun Onboardingscreen(
    onGettingStartedClick:()->Unit,
    onSkipClicked:()->Unit,
    modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState()
      Column(
          horizontalAlignment = Alignment.Start) {
          HorizontalPager(state = pagerState,
              count = 2,
              modifier = Modifier
                  .fillMaxWidth()
                  .weight(1f) ) { page ->
              PageUi(page = onboardPages[page])
          }
          HorizontalPagerIndicator(pagerState = pagerState,
              modifier = Modifier
                  .align(Alignment.CenterHorizontally)
                  .padding(16.dp),
              activeColor = GreenPrimary
          )


      }


}

val onboardPages = listOf(
    Page(
        1,
        "Temukan Nilai Gizi Secara Instant",
        "Pindai Makanan Anda dan Dapatkan Skor Nutrisi\n" +
                "Akurat dalam Hitungan Detik. Kita menggunakan\n" +
                "perhitungan Nutriscore yang sudah diterapkan di \n" +
                "Eropa",
        R.drawable.news_image3
    ),
    Page(
        2,
        "Integrasi WA yang mulus",
        "Uplooad photo langsung di whatsapp anda , kami akan memberikan skor nutrisi dan saran Makan sehat dalam hitungan detik",
        R.drawable.news_image3
    ),

)