package com.sopt.now.compose.ui.signIn

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sopt.now.compose.R
import com.sopt.now.compose.data.model.RequestSignInDto
import com.sopt.now.compose.ui.base.SoptInputTextField
import com.sopt.now.compose.ui.base.SoptOutlinedButton
import com.sopt.now.compose.ui.base.SoptPasswordTextField

@Composable
fun SignInScreen(
    onNavigateToHome: NavHostController,
    onNavigateToSignUp: () -> Unit,
    signInViewModel: SignInViewModel = viewModel(),
) {
    val context = LocalContext.current
    val signInState by signInViewModel.signInState.collectAsState()

    val id by signInViewModel.id.collectAsState()
    val password by signInViewModel.password.collectAsState()

    LaunchedEffect(signInState) {
        if (signInState.isSuccess) {
            Toast.makeText(context, signInState.message, Toast.LENGTH_SHORT).show()
            onNavigateToHome.navigate(context.getString(R.string.route_main))
        } else if (signInState.message.isNotBlank()) {
            Toast.makeText(context, signInState.message, Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        val isSignInButtonEnabled by remember(id, password) {
            mutableStateOf(
                id.isNotEmpty() && password.isNotEmpty()
            )
        }

        Text(
            text = stringResource(id = R.string.sign_in_screen_title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        Column {
            SoptInputTextField(
                text = R.string.label_id,
                value = id,
                onValueChange = { signInViewModel.updateId(it) }
            )
            SoptPasswordTextField(
                text = R.string.label_pw,
                value = password,
                onValueChange = { signInViewModel.updatePassword(it) }
            )
        }

        Column {
            SoptOutlinedButton(
                text = R.string.btn_sign_in,
                onClick = {
                    if (isSignInButtonEnabled) {
                        signInViewModel.signIn(
                            RequestSignInDto(
                                authenticationId = id,
                                password = password,
                            )
                        )
                    } else {
                        Toast.makeText(
                            context,
                            R.string.sign_up_fail,
                            Toast.LENGTH_SHORT
                        ).show()
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