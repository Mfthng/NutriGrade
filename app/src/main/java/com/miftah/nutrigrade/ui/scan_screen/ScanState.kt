package com.miftah.nutrigrade.ui.scan_screen

import com.miftah.nutrigrade.domain.Scanned
import com.miftah.nutrigrade.utils.UiState
import kotlinx.coroutines.flow.Flow

data class ScanState(
    val imageState: Flow<UiState<Scanned>>? = null,
    val title : String = ""
)