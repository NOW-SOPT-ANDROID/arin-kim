package com.sopt.now.compose.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen() {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item { HomeMyProfile() }
        items(30) { index ->
            HomeFriendProfile()
        }

        item { Text(text = "Last item") }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}