package com.example.bancot.util

import com.example.bancot.data.remote.model.AccountDto
import com.example.bancot.data.remote.model.PaymentDto
import com.example.bancot.models.classes.Account
import com.example.bancot.models.classes.Payment

fun AccountDto.toDomain(): Account {
    return Account(
        customerName = this.customerName,
        accountNumber = this.accountNumber,
        branchNumber = this.branchNumber,
        checkingAccountBalance = this.checkingAccountBalance,
        id = this.id
    )
}

fun PaymentDto.toDomain(): Payment {
    return Payment(
        paymentDate = this.paymentDate,
        electricityBill = this.electricityBill,
        id = this.id
    )
}