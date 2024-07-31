package com.miftah.nutrigrade.ui.onboarding_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.miftah.nutrigrade.R


@Composable
fun AnimatedPreloader(index : Int , modifier: Modifier) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
           if(index == 1) { R.raw.onboarding_1 } else {R.raw.onboarding_2}
        )
    )
    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = LottieConstants.IterateForever,
    )
    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preloaderProgress,
        modifier = modifier.height(300.dp)
    )
}
@Composable
fun PageUi(page: Page){
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.Start) {
        AnimatedPreloader(page.index,Modifier.fillMaxWidth())
        Text(
            text = page.title,
            fontSize = 28.sp, fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = page.description,
            textAlign = TextAlign.Start,fontSize = 14.sp)

    }

}