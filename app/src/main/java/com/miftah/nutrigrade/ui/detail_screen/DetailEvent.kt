package com.miftah.nutrigrade.ui.detail_screen

import com.miftah.nutrigrade.domain.Scanned

sealed class DetailEvent {
    data class SaveTODB(val data : Scanned) : DetailEvent()
}