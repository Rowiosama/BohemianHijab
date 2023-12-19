package com.rowiosama.bohemianhijabapp.di

import com.rowiosama.bohemianhijabapp.data.HijabRepository

object Injection {
    fun provideRepository(): HijabRepository {
        return HijabRepository.getInstance()
    }
}