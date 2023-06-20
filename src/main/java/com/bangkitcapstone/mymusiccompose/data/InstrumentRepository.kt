package com.bangkitcapstone.mymusiccompose.data

import com.bangkitcapstone.mymusiccompose.model.FakeInstrumentDataSource
import com.bangkitcapstone.mymusiccompose.model.OrderInstrument
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class InstrumentRepository() { //you add context there
    private val orderInstruments = mutableListOf<OrderInstrument>()
    init {
        if (orderInstruments.isEmpty()) {
            FakeInstrumentDataSource().dummyInstrument.forEach {
                orderInstruments.add(OrderInstrument(it, 0))
            }
        }
    }

    fun getAllInstrument(): Flow<List<OrderInstrument>> {
        return flowOf(orderInstruments)
    }

    fun getOrderInstrumentById(instrumentId: Long): OrderInstrument {
        return orderInstruments.first {
            it.instrument.id == instrumentId
        }
    }

    fun updateOrderInstrument(instrumentId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderInstruments.indexOfFirst { it.instrument.id == instrumentId }
        val result = if (index >= 0) {
            val orderInstrument = orderInstruments[index]
            orderInstruments[index] =
                orderInstrument.copy(instrument = orderInstrument.instrument, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderInstrument(): Flow<List<OrderInstrument>> {
        return getAllInstrument()
            .map { orderInstruments ->
                orderInstruments.filter { orderInstrument ->
                    orderInstrument.count != 0
                }
            }
    }

    companion object {
        @Volatile
        private var instance: InstrumentRepository? = null
        //you add context here
        fun getInstance(): InstrumentRepository =
            instance ?: synchronized(this) {
                InstrumentRepository().apply {
                    instance = this
                }
            }
    }
}