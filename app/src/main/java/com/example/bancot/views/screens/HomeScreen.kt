package com.example.bancot.views.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.bancot.R
import com.example.bancot.models.routNav.RoutNavigation
import com.example.bancot.viewModels.LoginScreenEvent
import com.example.bancot.viewModels.LoginScreenViewModel
import com.example.bancot.viewModels.uiState.LoginScreenUiState
import com.example.bancot.views.components.ErrorText
import com.example.bancot.views.components.OutlinedTextFieldDefault
import com.example.bancot.views.ui.theme.colorDefaultButton
import com.example.bancot.views.ui.theme.colorDefaultTextField

@Composable
fun HomeScreen(
    viewModel: LoginScreenViewModel,
    navController: NavHostController,
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.loginEvents.collect { event ->
            when (event) {
                LoginScreenEvent.NavigateToPayments -> {
                    navController.navigate(RoutNavigation.PaymentsScreen.rout) {
                        popUpTo(RoutNavigation.HomeScreen.rout) {
                            //     inclusive = true
                        }
                    }
                }
            }
        }
    }

    Scaffold()
    { paddingValues ->
        HomeScreen(
            state = state,
            onEmailChange = viewModel::onEmailChange,
            onPasswordChange = viewModel::onPasswordChange,
            onLoginClick = viewModel::onLoginClick,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun HomeScreen(
    state: LoginScreenUiState,
    onEmailChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
    onLoginClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        val (text, logo, emailField, passwordField, login, errorApi) = createRefs()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 28.dp)
                .constrainAs(text) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier,
                text = "Login",
                fontWeight = FontWeight(700),
                fontSize = 18.sp
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(logo) {
                    top.linkTo(text.bottom, margin = 25.dp)
                },
        ) {
            Image(
                modifier = Modifier
                    .heightIn(max = 358.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp)),
                painter = painterResource(R.drawable.aoe_logo),
                contentDescription = "Imagem do logo do banco",
                contentScale = ContentScale.FillWidth
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(emailField) {
                    top.linkTo(logo.bottom, margin = 16.dp)
                },
        ) {

            OutlinedTextFieldDefault(
                Modifier
                    .fillMaxWidth(),
                "Email",
                text = state.email,
                colorWithoutFocus = colorDefaultTextField,
                colorTextWithFocus = Color.Black,
                colorWithFocus = colorDefaultTextField,
                maxLine = 1,
                singleLine = true,
                keyBoardType = KeyboardType.Email,
                isError = state.errorEmail,
                textChanged = onEmailChange,
                supportingText = {
                    if (state.errorEmail) {
                        ErrorText("O email deve conter o símbolo '@' para ser válido.")
                    } else {
                        null
                    }
                }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(passwordField) {
                    top.linkTo(emailField.bottom, margin = 16.dp)
                },
        ) {
            OutlinedTextFieldDefault(
                Modifier
                    .fillMaxWidth(),
                "Password",
                text = state.password,
                colorWithoutFocus = colorDefaultTextField,
                colorTextWithFocus = Color.Black,
                colorWithFocus = colorDefaultTextField,
                maxLine = 1,
                singleLine = true,
                keyBoardType = KeyboardType.Password,
                isError = state.errorPassword,
                textChanged = onPasswordChange,
                supportingText = {
                    if (state.errorPassword) {
                        ErrorText(
                            textError = "A senha deve conter pelo menos 6 caracteres 1 sendo maiúsculo e 1 sendo número para ser válido."
                        )
                    } else {
                        null
                    }
                }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(login) {
                    top.linkTo(passwordField.bottom, margin = 12.dp)
                },
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                onClick = onLoginClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorDefaultButton,
                    contentColor = Color.White
                ),
                enabled = state.butonEnabled,
                shape = RoundedCornerShape(12.dp)

            ) {
                Text(text = "Login")
            }
        }
        if (state.apiErrorMessage != null) {
            Text(
                text = state.apiErrorMessage,
                color = Color.Red,
                modifier = Modifier.constrainAs(errorApi) {
                    top.linkTo(login.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    //HomeScreen()
}