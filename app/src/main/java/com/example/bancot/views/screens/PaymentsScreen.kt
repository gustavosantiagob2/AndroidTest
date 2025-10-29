package com.example.bancot.views.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.bancot.util.toBrazilianCurrency
import com.example.bancot.viewModels.PaymentScreenViewModel
import com.example.bancot.views.components.ContasPagasLazyColumn
import com.example.bancot.views.components.ContasPagasSkeletonLazyColumn
import com.example.bancot.views.ui.theme.colorDefaultTex
import java.math.BigDecimal


@Composable
fun PaymentsScreen(
    viewModel: PaymentScreenViewModel,
    navController : NavHostController
) {
    val paymentsState by viewModel.uiState.collectAsState()
    val account by viewModel.accountState.collectAsState()
    val saldo =  account?.checkingAccountBalance ?: 0

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        val (text, logo, email, password, login, contasPagas, primeira) = createRefs()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .constrainAs(text) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                modifier = Modifier
                    .clickable{ navController.popBackStack() },
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Icon back to page"
            )
            Text(
                modifier = Modifier
                    .weight(1f),
                textAlign = TextAlign.Center,
                text = "Pagamentos",
                fontWeight = FontWeight(700),
                fontSize = 18.sp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(logo) {
                    top.linkTo(text.bottom, margin = 38.dp)
                },
        ) {
            Text(
                text = "Detalhes do pagamento",
                fontWeight = FontWeight(700),
                fontSize = 22.sp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(email) {
                    top.linkTo(logo.bottom, margin = 32.dp)
                },
        ) {
            Text(
                text = "Cliente: ${account?.customerName}",
                fontSize = 16.sp,
                fontWeight = FontWeight(500)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(password) {
                    top.linkTo(email.bottom, margin = 16.dp)
                },
        ) {
            Text(
                text = "Agência: ${account?.branchNumber} | Conta: ${account?.accountNumber}",
                fontSize = 14.sp,
                color = colorDefaultTex
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(login) {
                    top.linkTo(password.bottom, margin = 16.dp)
                },
        ) {
            Text(
                text = "Saldo: ${BigDecimal(saldo.toDouble()).toBrazilianCurrency()}",
                fontWeight = FontWeight(400),
                fontSize = 16.sp,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(contasPagas) {
                    top.linkTo(login.bottom, margin = 16.dp)
                },
        ) {
            Text(
                text = "Contas pagas",
                fontWeight = FontWeight(700),
                fontSize = 22.sp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(primeira) {
                    top.linkTo(contasPagas.bottom, margin = 16.dp)
                },
        ) {
            if (paymentsState.isLoading) {
                ContasPagasSkeletonLazyColumn(count = 5)
            } else {
                ContasPagasLazyColumn(contas = paymentsState.payments)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PaymentsScreenPreview() {
    //PaymentsScreen()
}