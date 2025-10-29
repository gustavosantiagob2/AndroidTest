package com.example.bancot.views.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bancot.models.classes.Payment
import com.example.bancot.views.ui.theme.colorDefaultTex

@Composable
fun ContasPagas(conta: Payment) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Conta de luz ${conta.id} ",
                    fontWeight = FontWeight(500),
                    fontSize = 16.sp
                )
                Text(
                    text = conta.electricityBill,
                    color = colorDefaultTex,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Text(
                text = conta.paymentDate,
                color = colorDefaultTex,
                fontSize = 14.sp
            )
        }
    }
    Spacer(modifier = Modifier .height(32.dp))
}

@Preview(showSystemUi = true)
@Composable
fun PreviewItemContaPagas() {
//    ContasPagas(
////        conta = ContaPaga(
////            nome = "Conta de luz",
////            valor = "R$ 120,00",
////            data = "15/07/2024"
////        )
//    )
}