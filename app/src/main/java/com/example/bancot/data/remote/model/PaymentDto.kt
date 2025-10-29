package com.example.bancot.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class PaymentDto(
    val paymentDate: String,
    val electricityBill: String,
    val id: String,
)