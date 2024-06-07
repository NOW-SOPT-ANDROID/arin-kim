package com.sopt.now.compose.ui.myPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.now.compose.R

@Composable
fun MyPageScreen(
    myPageViewModel: MyPageViewModel = viewModel(),
) {
    val infoState by myPageViewModel.infoState.collectAsState()
    var offset by remember { mutableFloatStateOf(0f) }

    Column(modifier = Modifier
        .fillMaxSize()
        .scrollable(
            orientation = Orientation.Vertical,
            state = rememberScrollableState { delta ->
                offset += delta
                delta
            }
        ),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier.aspectRatio(3f / 4f)
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = infoState.data.authenticationId)
            Text(text = infoState.data.nickname)
            Text(text = infoState.data.phone)
        }
    }
}