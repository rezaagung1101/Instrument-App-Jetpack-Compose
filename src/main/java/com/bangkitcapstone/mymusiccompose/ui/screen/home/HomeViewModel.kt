package com.bangkitcapstone.mymusiccompose.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkitcapstone.mymusiccompose.data.InstrumentRepository
import com.bangkitcapstone.mymusiccompose.model.OrderInstrument
import com.bangkitcapstone.mymusiccompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: InstrumentRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderInstrument>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderInstrument>>>
        get() = _uiState

    fun getAllInstrument() {
        viewModelScope.launch {
            repository.getAllInstrument()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderInstrument ->
                    _uiState.value = UiState.Success(orderInstrument)
                }
        }
    }
}