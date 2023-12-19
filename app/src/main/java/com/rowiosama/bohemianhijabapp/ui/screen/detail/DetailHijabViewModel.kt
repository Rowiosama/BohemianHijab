package com.rowiosama.bohemianhijabapp.ui.screen.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rowiosama.bohemianhijabapp.data.HijabRepository
import com.rowiosama.bohemianhijabapp.model.AboutHijab
import com.rowiosama.bohemianhijabapp.ui.common.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailHijabViewModel(
    private val repository: HijabRepository
) : ViewModel() {
    data class ViewState(
        val uiState: UiState<AboutHijab>,
        val isFavorite: Boolean
    )

    private val _viewState: MutableStateFlow<ViewState> =
        MutableStateFlow(ViewState(uiState = UiState.Loading, isFavorite = false))
    val viewState: StateFlow<ViewState>
        get() = _viewState

    fun getHijabById(hijabId: String) {
        viewModelScope.launch {
            _viewState.value = _viewState.value.copy(uiState = UiState.Loading)
            val aboutAnime = repository.getHijabById(hijabId)
            _viewState.value = _viewState.value.copy(uiState = UiState.Success(aboutAnime), isFavorite = aboutAnime.isFavorite)
        }
    }

    fun toggleFavorite(horrorId: String) {
        viewModelScope.launch {
            val aboutHijab = repository.getHijabById(horrorId)
            aboutHijab.isFavorite = !aboutHijab.isFavorite
            repository.updateHijab(aboutHijab)
            _viewState.value = _viewState.value.copy(isFavorite = aboutHijab.isFavorite)
            Log.d("DetailHorrorViewModel", "isFavorite: ${aboutHijab.isFavorite}")
        }
    }

    fun getFavoriteHijab(): Flow<List<AboutHijab>> {
        return repository.getFavoriteHijab()
    }
}