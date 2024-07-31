package com.miftah.nutrigrade.core.remote.dto

import com.google.gson.annotations.SerializedName


data class ScanResponse(

    @field:SerializedName("data")
    val data: Data? = null,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class Data(

    @field:SerializedName("nutrion")
    val nutrition: Nutrition,

    @field:SerializedName("productPhoto")
    val productPhoto: String,

    @field:SerializedName("grade")
    val grade: String,

    @field:SerializedName("warnings")
    val warnings: List<String>,

    @field:SerializedName("positiveFeedback")
    val positiveFeedback: List<String>,

    @field:SerializedName("portion100g")
    val portion100g: Portion100g,

    @field:SerializedName("nutriScore")
    val nutriScore: Int
)

data class Nutrition(

    @field:SerializedName("totalCarbs")
    val totalCarbs: Int,

    @field:SerializedName("sodium")
    val sodium: Int,

    @field:SerializedName("totalFat")
    val totalFat: Int,

    @field:SerializedName("sugars")
    val sugars: Int,

    @field:SerializedName("dietaryFiber")
    val dietaryFiber: Int,

    @field:SerializedName("protein")
    val protein: Int,

    @field:SerializedName("cholesterol")
    val cholesterol: Int,

    @field:SerializedName("portionSize")
    val portionSize: Int,

    @field:SerializedName("energy")
    val energy: Int
)

data class Portion100g(

    @field:SerializedName("totalCarbs")
    val totalCarbs: Int,

    @field:SerializedName("sodium")
    val sodium: Int,

    @field:SerializedName("totalFat")
    val totalFat: Int,

    @field:SerializedName("sugars")
    val sugars: Int,

    @field:SerializedName("dietaryFiber")
    val dietaryFiber: Int,

    @field:SerializedName("protein")
    val protein: Int,

    @field:SerializedName("portionSize")
    val portionSize: String,

    @field:SerializedName("energy")
    val energy: Int
)
