package com.miftah.nutrigrade.ui.detail_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miftah.nutrigrade.domain.Repository
import com.miftah.nutrigrade.domain.Scanned
import com.miftah.nutrigrade.ui.scan_screen.ScanState
import dagger.hilt.android.lifecycle.HiltViewModel
import hilt_aggregated_deps._dagger_hilt_android_internal_managers_HiltWrapper_SavedStateHandleModule
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {
    private val _state = mutableStateOf(DetailState())
    val state: State<DetailState> get() = _state

    fun onEvent(detailEvent: DetailEvent) {
        when(detailEvent) {
            is DetailEvent.SaveTODB -> sendToDB(data = detailEvent.data)
        }
    }

    fun setScanned(scanState: Scanned) {
        _state.value = _state.value.copy(
            scanned = scanState
        )
    }

    fun sendToDB(data : Scanned) {
        viewModelScope.launch {
            repository.saveData(data)
        }
    }
}