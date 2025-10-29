package com.example.bancot

import android.app.Application
import com.example.bancot.data.dataStore.AccountPreferences
import com.example.bancot.data.remote.AccountApiService
import com.example.bancot.data.remote.RetrofitClient
import com.example.bancot.data.repository.AccountRepository

class MyApplication : Application() {
    private val accountApiService: AccountApiService by lazy {
        RetrofitClient.createService(AccountApiService::class.java)
    }

    private val userPreferences: AccountPreferences by lazy {
        AccountPreferences(this)
    }

    val accountRepository: AccountRepository by lazy {
        AccountRepository(
            apiService = accountApiService,
            userPreferences = userPreferences,
        )
    }

}