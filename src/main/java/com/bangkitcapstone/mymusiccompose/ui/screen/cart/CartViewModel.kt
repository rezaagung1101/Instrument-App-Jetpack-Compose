package com.bangkitcapstone.mymusiccompose.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkitcapstone.mymusiccompose.data.InstrumentRepository
import com.bangkitcapstone.mymusiccompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: InstrumentRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>>
        get() = _uiState

    fun getAddedOrderInstrument() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderInstrument()
                .collect { orderInstrument ->
                    val totalRequiredPoint =
                        orderInstrument.sumOf { it.instrument.price * it.count }
                    _uiState.value = UiState.Success(CartState(orderInstrument, totalRequiredPoint))
                }
        }
    }

    fun updateOrderInstrument(instrumentId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrderInstrument(instrumentId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderInstrument()
                    }
                }
        }
    }
}