package com.sopt.now.compose.ui.base

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun SoptOutlinedButton(@StringRes text: Int, onClick: () -> Unit) {
    OutlinedButton(onClick = { onClick() }, modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Text(text = stringResource(id = text))
    }
}