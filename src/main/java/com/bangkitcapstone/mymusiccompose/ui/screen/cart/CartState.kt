package com.bangkitcapstone.mymusiccompose.ui.screen.cart

import com.bangkitcapstone.mymusiccompose.model.OrderInstrument

data class CartState(
    val orderInstrument: List<OrderInstrument>,
    val totalRequiredMoney: Double //you update here to double
)