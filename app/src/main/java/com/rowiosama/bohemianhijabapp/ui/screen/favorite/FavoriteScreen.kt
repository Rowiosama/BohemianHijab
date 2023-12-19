package com.rowiosama.bohemianhijabapp.ui.screen.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rowiosama.bohemianhijabapp.R
import com.rowiosama.bohemianhijabapp.di.Injection
import com.rowiosama.bohemianhijabapp.ui.ViewModelFactory
import com.rowiosama.bohemianhijabapp.ui.components.FavoriteItem
import com.rowiosama.bohemianhijabapp.ui.screen.detail.DetailHijabViewModel

@Composable
fun FavScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailHijabViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    )
) {
    val favoriteHijab by viewModel.getFavoriteHijab().collectAsState(emptyList())

    if (favoriteHijab.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(stringResource(R.string.no_favorite_items), textAlign = TextAlign.Center)
        }
    } else {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = modifier.fillMaxWidth()
        ) {
            items(favoriteHijab) { data ->
                FavoriteItem(title = data.hijab.title, imageUrl = data.hijab.imageUrl, desc = data.hijab.desc , isFavorite = data.isFavorite)
            }
        }
    }
}