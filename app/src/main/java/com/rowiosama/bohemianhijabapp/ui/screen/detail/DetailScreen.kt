package com.rowiosama.bohemianhijabapp.ui.screen.detail

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.rowiosama.bohemianhijabapp.R
import com.rowiosama.bohemianhijabapp.di.Injection
import com.rowiosama.bohemianhijabapp.model.AboutHijab
import com.rowiosama.bohemianhijabapp.ui.ViewModelFactory
import com.rowiosama.bohemianhijabapp.ui.common.UiState
import com.rowiosama.bohemianhijabapp.ui.theme.HorrorMoviezAppTheme

@Composable
fun DetailScreen(
    horrorId: String,
    viewModel: DetailHijabViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit
) {
    val viewState by viewModel.viewState.collectAsState()

    when (viewState.uiState) {
        is UiState.Loading -> {
            viewModel.getHijabById(horrorId)
        }
        is UiState.Success -> {
            val data = (viewState.uiState as UiState.Success<AboutHijab>).data
            DetailContent(
                imageUrl = data.hijab.imageUrl,
                title = data.hijab.title,
                desc = data.hijab.desc,
                isFavorite = viewState.isFavorite,
                onFavoriteClick = {
                    viewModel.toggleFavorite(horrorId)
                },
                onBackClick = navigateBack
            )
        }
        is UiState.Error -> {
            // Handle error state
        }
    }
}


@Composable
fun FavoriteFloatingActionButton(
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val icon = if (isFavorite) {
        Icons.Default.Favorite
    } else {
        Icons.Default.FavoriteBorder
    }

    ExtendedFloatingActionButton(
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        },
        text = {

        },
        onClick = { onFavoriteClick() },
        modifier = modifier
    )
}

@Composable
fun DetailContent(
    @SuppressLint("SupportAnnotationUsage") @DrawableRes
    imageUrl: String,
    title: String,
    desc: String,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = desc,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    textAlign = TextAlign.Justify
                )
                Spacer(modifier = Modifier.height(16.dp))
                FavoriteFloatingActionButton(
                    isFavorite = isFavorite,
                    onFavoriteClick = onFavoriteClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    HorrorMoviezAppTheme {

    }
}