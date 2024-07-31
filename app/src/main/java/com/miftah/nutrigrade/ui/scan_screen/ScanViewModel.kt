package com.miftah.nutrigrade.ui.scan_screen

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.miftah.nutrigrade.domain.Repository
import com.miftah.nutrigrade.utils.uriToFile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class ScanViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {
    private val _state = mutableStateOf(ScanState())
    val state: State<ScanState> get() = _state

    fun onEvent(scanEvent: ScanEvent) {
        when (scanEvent) {
            is ScanEvent.ScanToCLod -> sendToCloud(scanEvent.data, scanEvent.context)
        }
    }

    fun sendToCloud(uri : Uri, context : Context) {
        _state.value = _state.value.copy(
            imageState = repository.scanImage(uriToFile(uri, context))
        )
    }
}