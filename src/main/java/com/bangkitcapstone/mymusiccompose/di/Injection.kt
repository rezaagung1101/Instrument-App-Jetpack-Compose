package com.bangkitcapstone.mymusiccompose.di

import com.bangkitcapstone.mymusiccompose.data.InstrumentRepository


object Injection {
    fun provideRepository(): InstrumentRepository {
        return InstrumentRepository.getInstance()
    }
}