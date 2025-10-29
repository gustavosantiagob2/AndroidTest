package com.example.bancot.viewModels.uiState

import com.example.bancot.models.classes.Payment

data class PaymentScreenUiState(
    val payments: List<Payment> = emptyList(),
    val isLoading: Boolean = false,
    val apiErrorMessage: String? = null,
)

