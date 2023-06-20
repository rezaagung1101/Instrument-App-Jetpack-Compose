package com.bangkitcapstone.mymusiccompose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkitcapstone.mymusiccompose.data.InstrumentRepository
import com.bangkitcapstone.mymusiccompose.ui.screen.cart.CartViewModel
import com.bangkitcapstone.mymusiccompose.ui.screen.detail.DetailInstrumentViewModel
import com.bangkitcapstone.mymusiccompose.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: InstrumentRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailInstrumentViewModel::class.java)) {
            return DetailInstrumentViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}