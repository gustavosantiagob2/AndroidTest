package com.example.bancot.models.classes

data class Account(
    val customerName: String,
    val accountNumber: String,
    val branchNumber: String,
    val checkingAccountBalance: Double,
    val id: String
)
