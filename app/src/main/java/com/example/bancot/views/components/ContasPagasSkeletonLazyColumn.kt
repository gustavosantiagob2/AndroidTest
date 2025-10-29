package com.example.bancot.views.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

@Composable
fun ContasPagasSkeletonLazyColumn(count: Int = 4) {
    Column {
        repeat(count) {
            PaymentSkeletonItem()
        }
    }
}