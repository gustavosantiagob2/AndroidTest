package com.example.bancot.data.repository

import com.example.bancot.data.dataStore.AccountPreferences
import com.example.bancot.data.remote.AccountApiService
import com.example.bancot.models.classes.Account
import com.example.bancot.models.classes.Payment
import com.example.bancot.util.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class AccountRepository(
    private val apiService: AccountApiService,
    private val userPreferences: AccountPreferences,
) {

    val accountFlow: Flow<Account?> = combine(
        userPreferences.customerName,
        userPreferences.accountNumber,
        userPreferences.branchNumber,
        userPreferences.accountBalance,
        userPreferences.id
    ) { name, number, branch, balance, id ->

        if (name == null || balance == null || number == null || branch == null || id == null) {
            null
        } else {
            Account(
                customerName = name,
                accountNumber = number,
                branchNumber = branch, // ⭐️ O branch DEVE ser usado aqui
                checkingAccountBalance = balance,
                id = id
            )
        }
    }

    //Estou salvando no DataStore por isso não estou usando
    suspend fun infoAccount(): Account {
        val accountDtoList = apiService.getInfoAccount()
        val accountDto = accountDtoList.first()
        val account = accountDto.toDomain()
        userPreferences.saveAccountInfo(account)
        return account
    }

    suspend fun getPayments(): List<Payment> {
        val paymentDtoList = apiService.getPayments()
        return paymentDtoList.map { it.toDomain() }
    }

}

