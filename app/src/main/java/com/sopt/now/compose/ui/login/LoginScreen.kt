package com.sopt.now.compose.ui.login

import android.util.Log
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

@Composable
fun LoginScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToSignUp: () -> Unit,
    id: String,
    pw: String,
    nickname: String,
    mbti: String,
) {
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


        val isLoginButtonEnabled by remember(textId, textPw) {
            mutableStateOf(
                textId.isNotEmpty() && textPw.isNotEmpty()
            )
        }

        Text(
            text = stringResource(id = R.string.login_screen_title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        Column {
            SoptInputTextField(
                text = R.string.label_id, value = textId, onValueChange = { textId = it }
            )
            SoptPasswordTextField(
                text = R.string.label_pw, value = textPw, onValueChange = { textPw = it }
            )
        }

        Column {
            SoptOutlinedButton(
                text = R.string.btn_login,
                onClick = {
                    Log.d("Login", "$textId, $id")
                    if (textId == id && textPw == pw && isLoginButtonEnabled) {
                        onNavigateToHome()
                    }
                },
                enabled = true
            )
            SoptOutlinedButton(
                text = R.string.btn_sign_up,
                onClick = onNavigateToSignUp,
                enabled = true
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        onNavigateToHome = {},
        onNavigateToSignUp = {},
        id = "",
        pw = "",
        nickname = "",
        mbti = ""
    )
}