package com.miftah.nutrigrade.core.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.miftah.nutrigrade.core.local.entity.ScanEntity


@Database(
    entities = [ScanEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDB : RoomDatabase(){
    abstract fun scanDao() : ScanDao
}