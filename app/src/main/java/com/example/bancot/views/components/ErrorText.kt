package com.example.bancot.views.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun ErrorText(textError : String) {
    Text(
        text = textError,
        fontSize = 12.sp,
        color = MaterialTheme.colorScheme.error
    )
}