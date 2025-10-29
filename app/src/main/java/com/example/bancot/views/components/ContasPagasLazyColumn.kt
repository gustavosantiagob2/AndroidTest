package com.example.bancot.views.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.bancot.models.classes.Payment


@Composable
fun ContasPagasLazyColumn(contas: List<Payment>) {
    LazyColumn {
        items(contas) { conta ->
            ContasPagas(conta = conta)
        }
    }
}
