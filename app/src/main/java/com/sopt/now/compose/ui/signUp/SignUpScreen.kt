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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sopt.now.compose.R
import com.sopt.now.compose.data.model.RequestSignUpDto
import com.sopt.now.compose.ui.base.SoptInputTextField
import com.sopt.now.compose.ui.base.SoptOutlinedButton
import com.sopt.now.compose.ui.base.SoptPasswordTextField
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(
    onNavigateToSignIn: NavHostController,
    signUpViewModel: SignUpViewModel = viewModel(),
) {
    val context = LocalContext.current
    val signUpState by signUpViewModel.signUpState.collectAsState()

    val id by signUpViewModel.id.collectAsState()
    val password by signUpViewModel.password.collectAsState()
    val nickname by signUpViewModel.nickname.collectAsState()
    val phoneNumber by signUpViewModel.phoneNumber.collectAsState()

    LaunchedEffect(signUpState) {
        if (signUpState.isSuccess) {
            Toast.makeText(context, signUpState.message, Toast.LENGTH_SHORT).show()
            onNavigateToSignIn.navigate(context.getString(R.string.route_sign_in))
        } else if (signUpState.message.isNotBlank()) {
            Toast.makeText(context, signUpState.message, Toast.LENGTH_SHORT).show()
        }
    }

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

        val isSignUpButtonEnabled by remember(id, password, nickname, phoneNumber) {
            mutableStateOf(
                id.length in 6..10 && password.length in 8..12 && nickname.isNotEmpty() && !nickname.contains(
                    " "
                ) && phoneNumber.matches(Regex("^010-\\d{4}-\\d{4}\$"))
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
                    value = id,
                    onValueChange = { signUpViewModel.updateId(it) })
                SoptPasswordTextField(
                    text = R.string.hint_pw,
                    value = password,
                    onValueChange = { signUpViewModel.updatePassword(it) })
                SoptInputTextField(
                    text = R.string.hint_nickname,
                    value = nickname,
                    onValueChange = { signUpViewModel.updateNickname(it) })
                SoptInputTextField(
                    text = R.string.hint_phone,
                    value = phoneNumber,
                    onValueChange = { signUpViewModel.updatePhoneNumber(it) })
            }
        }
        SoptOutlinedButton(text = R.string.btn_sign_up, onClick = {
            if (isSignUpButtonEnabled) {
                signUpViewModel.signUp(
                    RequestSignUpDto(
                        authenticationId = id,
                        password = password,
                        nickname = nickname,
                        phone = phoneNumber
                    )
                )
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
