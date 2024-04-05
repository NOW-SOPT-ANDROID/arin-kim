package com.sopt.now.compose.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.R
import com.sopt.now.compose.ui.base.SoptInputTextField
import com.sopt.now.compose.ui.base.SoptOutlinedButton
import com.sopt.now.compose.ui.base.SoptPasswordTextField
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme

@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        var textId by remember {
            mutableStateOf("")
        }
        var textPw by remember {
            mutableStateOf("")
        }
        Text(
            text = stringResource(id = R.string.login_screen_title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        Column{
            SoptInputTextField(
                value = textId, text = R.string.id_label, onValueChange = { textId = it }
            )
            SoptPasswordTextField(
                value = textPw, onValueChange = { textPw = it }
            )
        }

        SoptOutlinedButton(text = R.string.btn_login, onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    NOWSOPTAndroidTheme {
        LoginScreen()
    }
}