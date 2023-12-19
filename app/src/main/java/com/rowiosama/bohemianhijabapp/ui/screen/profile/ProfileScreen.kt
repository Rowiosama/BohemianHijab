package com.rowiosama.bohemianhijabapp.ui.screen.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.rowiosama.bohemianhijabapp.R
import com.rowiosama.bohemianhijabapp.model.Profile

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        ProfileContent()
    }
}

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier
){
    val profile = Profile(
        "Bohemian Era",
        "Try Your Look",
        "Capstone Project",
        R.drawable.bohemian
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = profile.image,
            contentDescription = "profile_image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(
                    bottom = 16.dp,
                )
                .size(300.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Divider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp))
        Text(
            text = profile.name,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                )
                .fillMaxWidth()
        )
        Text(
            text = profile.nick,
            fontSize = 19.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                )
                .fillMaxWidth()
        )

        Text(
            text = profile.email,
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                )
                .fillMaxWidth()
        )

    }
}