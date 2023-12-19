package com.rowiosama.bohemianhijabapp.ui.screen.all

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rowiosama.bohemianhijabapp.di.Injection
import com.rowiosama.bohemianhijabapp.model.AboutHijab
import com.rowiosama.bohemianhijabapp.ui.ViewModelFactory
import com.rowiosama.bohemianhijabapp.ui.common.UiState
import com.rowiosama.bohemianhijabapp.ui.components.HijabItem
import com.rowiosama.bohemianhijabapp.ui.components.SearchBar

@Composable
fun AllScreen(
    modifier: Modifier = Modifier,
    viewModel: AllViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (String) -> Unit,
    navigateToFavorite: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllRewards()
            }
            is UiState.Success -> {
                Column(modifier = modifier
                    .fillMaxSize()
                ) {
                    SearchBar(
                        query = searchQuery,
                        onQueryChange = { newQuery ->
                            searchQuery = newQuery
                            viewModel.searchHorror(newQuery)
                        },
                        navigateToFavorite = {
                            navigateToFavorite()
                        }
                    )
                    AllHorrorContent(
                        aboutHijab = uiState.data,
                        modifier = modifier,
                        navigateToDetail = navigateToDetail,
                    )
                }
            }
            is UiState.Error -> {}
            else -> {}
        }
    }
}

@Composable
fun AllHorrorContent(
    aboutHijab: List<AboutHijab>,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(100.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(aboutHijab) { data ->
            HijabItem(
                title = data.hijab.title,
                imageUrl = data.hijab.imageUrl,
                modifier = Modifier.clickable {
                    navigateToDetail(data.hijab.id)
                }
            )
        }
    }
}