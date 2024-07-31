package com.miftah.nutrigrade.ui.home_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.miftah.nutrigrade.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map

@HiltViewModel
class HomeViewModel constructor(
    val repository: Repository
) : ViewModel() {
    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> get() = _state

    fun observe() {
        _state.value = _state.value.copy(
            history = repository.getData()
        )
    }

    init {
        observe()
    }
}