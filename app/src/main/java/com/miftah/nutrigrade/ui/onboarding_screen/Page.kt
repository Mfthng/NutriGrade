package com.miftah.nutrigrade.ui.onboarding_screen

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

data class Page(
    val index: Int,
    val title: String,
                val description: String,
                @DrawableRes val image:Int)