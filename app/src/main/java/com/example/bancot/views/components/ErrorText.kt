package com.example.bancot.views.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ErrorText(textError : String) {
    Text(
        text = textError,
        color = MaterialTheme.colorScheme.error
    )
}