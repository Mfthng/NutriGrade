package com.miftah.nutrigrade.core.local.room

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.miftah.nutrigrade.core.local.entity.ScanEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScanDao{
    @Query("SELECT * FROM scan_entity")
    fun getAll() : Flow<List<ScanEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(data : ScanEntity)
}