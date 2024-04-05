package com.sopt.now.compose.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.sopt.now.compose.R

@Composable
fun HomeScreen(
    id: String,
    pw: String,
    nickname: String,
    mbti: String,
) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier.aspectRatio(3f / 4f)
        )
        Column {
            Text(text = id)
            Text(text = pw)
            Text(text = nickname)
            Text(text = mbti)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        id = "",
        pw = "",
        nickname = "",
        mbti = "",
    )
}