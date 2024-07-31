package com.miftah.nutrigrade.ui.detail_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.miftah.nutrigrade.ui.scan_screen.ScanState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {
    private val _state = mutableStateOf(ScanState())
    val state: State<ScanState> get() = _state
}