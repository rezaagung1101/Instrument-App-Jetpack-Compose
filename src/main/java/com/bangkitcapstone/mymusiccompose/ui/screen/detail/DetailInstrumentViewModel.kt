package com.bangkitcapstone.mymusiccompose.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkitcapstone.mymusiccompose.data.InstrumentRepository
import com.bangkitcapstone.mymusiccompose.model.Instrument
import com.bangkitcapstone.mymusiccompose.model.OrderInstrument
import com.bangkitcapstone.mymusiccompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailInstrumentViewModel(
    private val repository: InstrumentRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderInstrument>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderInstrument>>
        get() = _uiState

    fun getInstrumentById(instrumentId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderInstrumentById(instrumentId))
        }
    }

    fun addToCart(instrument: Instrument, count: Int) {
        viewModelScope.launch {
            repository.updateOrderInstrument(instrument.id, count)
        }
    }
}