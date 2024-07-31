package com.miftah.nutrigrade.ui.home_screen

import androidx.room.Entity
import com.miftah.nutrigrade.core.local.entity.ScanEntity
import com.miftah.nutrigrade.domain.Scanned
import com.miftah.nutrigrade.utils.UiState
import kotlinx.coroutines.flow.Flow

data class HomeState(
    val history : Flow<List<ScanEntity>>? = null
)