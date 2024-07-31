package com.miftah.nutrigrade.core.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("scan_entity")
data class ScanEntity(
    @PrimaryKey(true)
    @ColumnInfo(name = "id")
    val id : Int = 0,

    @ColumnInfo("totalCarbs")
    val totalCarbs: Int,

    @ColumnInfo("sodium")
    val sodium: Int,

    @ColumnInfo("totalFat")
    val totalFat: Int,

    @ColumnInfo("sugars")
    val sugars: Int,

    @ColumnInfo("dietaryFiber")
    val dietaryFiber: Int,

    @ColumnInfo("protein")
    val protein: Int,

    @ColumnInfo("cholesterol")
    val cholesterol: Int,

    @ColumnInfo("portionSize")
    val portionSize: Int,

    @ColumnInfo("energy")
    val energy: Int,

    @ColumnInfo("productPhoto")
    val productPhoto: String,

    @ColumnInfo("grade")
    val grade: String,

    @ColumnInfo("warnings")
    val warnings: String,

    @ColumnInfo("positiveFeedback")
    val positiveFeedback: String,

    @ColumnInfo("totalCarbs100g")
    val totalCarbs100g: Int,

    @ColumnInfo("sodium100g")
    val sodium100g: Int,

    @ColumnInfo("totalFat100g")
    val totalFat100g: Int,

    @ColumnInfo("sugars100g")
    val sugars100g: Int,

    @ColumnInfo("dietaryFiber100g")
    val dietaryFiber100g: Int,

    @ColumnInfo("protein100g")
    val protein100g: Int,

    @ColumnInfo("portionSize100g")
    val portionSize100g: String,

    @ColumnInfo("energy100g")
    val energy100g: Int,

    @ColumnInfo("nutriScore")
    val nutriScore: Int
)
