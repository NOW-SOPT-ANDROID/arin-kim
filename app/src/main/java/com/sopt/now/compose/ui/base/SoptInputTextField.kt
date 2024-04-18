package com.sopt.now.compose.ui.base

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun SoptInputTextField(
    @StringRes text: Int,
    value: String = "",
    onValueChange: (String) -> Unit = {},
) {
    OutlinedTextField(
        value = value, onValueChange = onValueChange,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        label = { Text(text = stringResource(id = text)) },
        maxLines = 1,
    )
}