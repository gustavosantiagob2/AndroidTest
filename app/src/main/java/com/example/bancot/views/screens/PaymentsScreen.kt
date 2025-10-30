package com.example.bancot.views.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
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
import com.example.bancot.views.components.AccountsPayLazyColumn
import com.example.bancot.views.components.AccountsPaySkeletonLazyColumn
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
            .fillMaxHeight()
            .padding(horizontal = 16.dp)
    ) {
        val (header, details, client, password, infoAccount, AccountsPay, Accounts) = createRefs()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .constrainAs(header) {
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
                .constrainAs(details) { top.linkTo(header.bottom, margin = 20.dp) }
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
                .constrainAs(client) { top.linkTo(details.bottom, margin = 14.dp) }
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
                .constrainAs(password) { top.linkTo(client.bottom, margin = 1.dp) }
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
                .constrainAs(infoAccount) { top.linkTo(password.bottom, margin = 13.dp) }
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
                .constrainAs(AccountsPay) { top.linkTo(infoAccount.bottom, margin = 16.dp) }
        ) {
            Text(
                text = "Contas pagas",
                fontWeight = FontWeight(700),
                fontSize = 22.sp
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 500.dp)
                .constrainAs(Accounts) {
                    top.linkTo(AccountsPay.bottom, margin = 12.dp)
                }
        ) {
            if (paymentsState.isLoading) {
                AccountsPaySkeletonLazyColumn(count = 7)
            } else {
                AccountsPayLazyColumn(accounts = paymentsState.payments,)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PaymentsScreenPreview() {
    //PaymentsScreen()
}