package com.miftah.nutrigrade.domain

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.miftah.nutrigrade.core.local.entity.ScanEntity
import com.miftah.nutrigrade.core.remote.dto.ScanResponse
import com.miftah.nutrigrade.utils.AppUtils
import kotlinx.parcelize.Parcelize

@Parcelize
data class Scanned(
    val id : Int = 0,
    val totalCarbs: Int,
    val sodium: Int,
    val totalFat: Int,
    val sugars: Int,
    val dietaryFiber: Int,
    val protein: Int,
    val cholesterol: Int,
    val portionSize: Int,
    val energy: Int,
    val productPhoto: String,
    val grade: String,
    val warnings: String, // <--
    val positiveFeedback: String, // <--
    val totalCarbs100g: Int,
    val sodium100g: Int,
    val totalFat100g: Int,
    val sugars100g: Int,
    val dietaryFiber100g: Int,
    val protein100g: Int,
    val portionSize100g: String,
    val energy100g: Int,
    val nutriScore: Int
) : Parcelable

fun Scanned.toScannedEntity() : ScanEntity{
    return ScanEntity(
        sugars100g = sugars100g,
        grade = grade,
        energy = energy,
        positiveFeedback = positiveFeedback,
        sugars = sugars,
        sodium = sodium,
        totalFat = totalFat,
        protein = protein,
        dietaryFiber = dietaryFiber,
        totalCarbs = totalCarbs,
        portionSize = portionSize,
        warnings = warnings,
        nutriScore = nutriScore,
        productPhoto = productPhoto,
        cholesterol = cholesterol,
        dietaryFiber100g = dietaryFiber100g,
        totalCarbs100g = totalCarbs100g,
        portionSize100g = portionSize100g,
        totalFat100g = totalFat100g,
        sodium100g = sodium100g,
        energy100g = energy100g,
        protein100g = protein100g
    )
}

fun ScanEntity.toScanned() : Scanned {
    return Scanned(
        portionSize = portionSize,
        warnings = warnings,
        nutriScore = nutriScore,
        cholesterol = cholesterol,
        productPhoto = productPhoto,
        dietaryFiber = dietaryFiber,
        totalCarbs = totalCarbs,
        protein = protein,
        totalFat = totalFat,
        sodium = sodium,
        sugars = sugars,
        positiveFeedback = warnings,
        energy = energy,
        grade = grade,
        portionSize100g = portionSize100g,
        protein100g = protein100g,
        totalCarbs100g = totalCarbs100g,
        energy100g = energy100g,
        totalFat100g = totalFat100g,
        sodium100g = sodium100g,
        dietaryFiber100g = dietaryFiber100g,
        sugars100g = sugars100g
    )
}

fun ScanResponse.toScanned() : Scanned {
    val result : Scanned
    this.data!!.let {
        result = Scanned(
            grade = it.grade,
            sugars = it.nutrition.sugars,
            energy = it.nutrition.energy,
            positiveFeedback = AppUtils.fromListToString(it.positiveFeedback),
            sodium = it.nutrition.sodium,
            totalFat = it.nutrition.totalFat,
            protein = it.nutrition.protein,
            totalCarbs = it.nutrition.totalCarbs,
            dietaryFiber = it.nutrition.dietaryFiber,
            productPhoto = it.productPhoto,
            cholesterol = it.nutrition.cholesterol,
            nutriScore = it.nutriScore,
            portionSize = it.nutrition.portionSize,
            warnings = AppUtils.fromListToString(it.warnings),
            sugars100g = it.portion100g.sugars,
            portionSize100g = it.portion100g.portionSize,
            totalCarbs100g = it.portion100g.totalCarbs,
            energy100g = it.portion100g.energy,
            totalFat100g = it.portion100g.totalFat,
            sodium100g = it.portion100g.sodium,
            dietaryFiber100g = it.portion100g.dietaryFiber,
            protein100g = it.portion100g.protein
        )
    }
    return result
}