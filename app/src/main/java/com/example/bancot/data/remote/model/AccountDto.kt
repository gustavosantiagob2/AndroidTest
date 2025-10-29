package com.example.bancot.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class AccountDto(
    val customerName: String,
    val accountNumber: String,
    val branchNumber: String,
    val checkingAccountBalance: Double,
    val id: String
)