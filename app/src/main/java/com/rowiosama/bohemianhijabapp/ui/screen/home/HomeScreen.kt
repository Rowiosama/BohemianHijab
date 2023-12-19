package com.rowiosama.bohemianhijabapp.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.rowiosama.bohemianhijabapp.R
import com.rowiosama.bohemianhijabapp.di.Injection
import com.rowiosama.bohemianhijabapp.model.AboutHijab
import com.rowiosama.bohemianhijabapp.ui.ViewModelFactory
import com.rowiosama.bohemianhijabapp.ui.common.UiState
import com.rowiosama.bohemianhijabapp.ui.components.DotsIndicator
import com.rowiosama.bohemianhijabapp.ui.components.HijabItem
import com.rowiosama.bohemianhijabapp.ui.components.SearchBar
import com.rowiosama.bohemianhijabapp.ui.components.SectionText
import kotlinx.coroutines.delay
import java.util.Random

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (String) -> Unit,
    navigateToFavorite: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllHijab()
            }
            is UiState.Success -> {
                Column(modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                ) {
                    SearchBar(
                        query = searchQuery,
                        onQueryChange = { newQuery ->
                            searchQuery = newQuery
                            viewModel.searchHijab(newQuery)
                        },
                        navigateToFavorite = {
                            navigateToFavorite()
                        }
                    )

                    if (searchQuery.isEmpty()) {
                        Banner()
                        SectionText(stringResource(R.string.section_trending))
                    }

                    HijabRowContent(
                        aboutHijab = uiState.data,
                        modifier = Modifier,
                        navigateToDetail = navigateToDetail,
                    )

                    if (searchQuery.isEmpty()) {
                        SectionText(stringResource(R.string.section_terbaru))
                    }

                    if (searchQuery.isEmpty()) {
                        HijabRowContent(
                            aboutHijab = uiState.data,
                            modifier = Modifier,
                            navigateToDetail = navigateToDetail,
                        )
                    }
                }
            }
            is UiState.Error -> {}
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Banner(modifier: Modifier = Modifier) {
    val bannerImages = listOf(
        R.drawable.homehijab1,
        R.drawable.homehijab2,
        R.drawable.homehijab3
    )

    val pagerState = rememberPagerState(pageCount = bannerImages.size)
    LaunchedEffect(Unit) {
        while (true) {
            delay(5000)
            pagerState.animateScrollToPage((pagerState.currentPage + 1) % pagerState.pageCount)
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { currentPage ->
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .aspectRatio(1.5f),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Box {
                        Image(
                            painter = painterResource(id = bannerImages[currentPage]),
                            contentDescription = "",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                        DotsIndicator(
                            modifier = Modifier
                                .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
                                .align(Alignment.BottomCenter),
                            pageCount = bannerImages.size,
                            currentPage = pagerState.currentPage
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HijabRowContent(
    aboutHijab: List<AboutHijab>,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit
) {
    val randomHorror = aboutHijab.shuffled(Random(System.currentTimeMillis())).take(5)

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        items(randomHorror) { data ->
            HijabItem(title = data.hijab.title, imageUrl = data.hijab.imageUrl, modifier = Modifier
                .padding(4.dp)
                .clickable {
                    navigateToDetail(data.hijab.id)
                })
        }
    }
}