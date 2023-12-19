package com.rowiosama.bohemianhijabapp.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DotsIndicator(modifier: Modifier, pageCount: Int, currentPage:Int) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) {
            Dot(isSelected = it == currentPage, modifier = modifier)
        }
    }
}

@Composable
fun Dot(isSelected: Boolean, modifier: Modifier) {
    val size = animateDpAsState(targetValue = if(isSelected) 8.dp else 6.dp, label = "")
    Box(modifier = modifier.padding(2.dp).size(size.value).clip(CircleShape).background(if(isSelected) Color.LightGray else Color.Gray))
}