package com.rowiosama.bohemianhijabapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rowiosama.bohemianhijabapp.data.HijabRepository
import com.rowiosama.bohemianhijabapp.ui.screen.all.AllViewModel
import com.rowiosama.bohemianhijabapp.ui.screen.detail.DetailHijabViewModel
import com.rowiosama.bohemianhijabapp.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: HijabRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(AllViewModel::class.java)) {
            return AllViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailHijabViewModel::class.java)) {
            return DetailHijabViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}