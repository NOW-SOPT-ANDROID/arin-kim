package com.sopt.now.compose.ui.signUp

import android.widget.Toast
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.now.compose.R
import com.sopt.now.compose.ui.base.SoptInputTextField
import com.sopt.now.compose.ui.base.SoptOutlinedButton
import com.sopt.now.compose.ui.base.SoptPasswordTextField
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    onNavigateToLogin: () -> Unit,
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        val scrollState = rememberScrollState()
        val coroutineScope = rememberCoroutineScope()
        val keyboardHeight = WindowInsets.ime.getBottom(LocalDensity.current)

        LaunchedEffect(key1 = keyboardHeight) {
            coroutineScope.launch {
                scrollState.scrollBy(keyboardHeight.toFloat())
            }
        }

        var textId by remember {
            mutableStateOf("")
        }
        var textPw by remember {
            mutableStateOf("")
        }
        var textNickname by remember {
            mutableStateOf("")
        }
        var textMbti by remember {
            mutableStateOf("")
        }

        val isSignUpButtonEnabled by remember(textId, textPw, textNickname, textMbti) {
            mutableStateOf(
                textId.length in 6..10 && textPw.length in 8..12 && textNickname.isNotEmpty() && !textNickname.contains(
                    " "
                ) && textMbti.length == 4
            )
        }

        Text(
            text = stringResource(id = R.string.sign_up_title),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )
        LazyColumn {
            item {
                SoptInputTextField(
                    text = R.string.hint_id,
                    value = textId,
                    onValueChange = { textId = it })
                SoptPasswordTextField(
                    text = R.string.hint_pw,
                    value = textPw,
                    onValueChange = { textPw = it })
                SoptInputTextField(
                    text = R.string.hint_nickname,
                    value = textNickname,
                    onValueChange = { textNickname = it })
                SoptInputTextField(
                    text = R.string.hint_mbti,
                    value = textMbti,
                    onValueChange = { textMbti = it })
            }
        }
        SoptOutlinedButton(text = R.string.btn_sign_up, onClick = {
            if (isSignUpButtonEnabled) {
                Toast.makeText(
                    context,
                    "회원가입 성공",
                    Toast.LENGTH_SHORT
                ).show()
                onNavigateToLogin()
            } else {
                Toast.makeText(
                    context,
                    "조건을 만족하지 않습니다",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }, enabled = true)
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(onNavigateToLogin = {})
}