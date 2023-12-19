package com.rowiosama.bohemianhijabapp.ui.screen.all

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rowiosama.bohemianhijabapp.data.HijabRepository
import com.rowiosama.bohemianhijabapp.model.AboutHijab
import com.rowiosama.bohemianhijabapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class AllViewModel(private val repository: HijabRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<AboutHijab>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<AboutHijab>>>
        get() = _uiState

    fun getAllRewards() {
        viewModelScope.launch {
            repository.getAllHijab()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { favHijab ->
                    _uiState.value = UiState.Success(favHijab)
                }
        }
    }

    fun searchHorror(query: String) {
        viewModelScope.launch {
            repository.searchHijab(query)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { searchedAnime ->
                    _uiState.value = UiState.Success(searchedAnime)
                }
        }
    }
}