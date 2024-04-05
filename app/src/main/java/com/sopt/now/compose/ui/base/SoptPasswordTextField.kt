package com.sopt.now.compose.ui.base

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.sopt.now.compose.R

@Composable
fun SoptPasswordTextField(
    value: String = "",
    onValueChange: (String) -> Unit = {},
) {
    OutlinedTextField(
        value = value, onValueChange = onValueChange,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        label = { Text(text = stringResource(id = R.string.pw_label)) },
        maxLines = 1,
        visualTransformation = PasswordVisualTransformation()
    )
}