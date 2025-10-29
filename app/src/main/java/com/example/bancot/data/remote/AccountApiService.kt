package com.example.bancot.data.remote

import com.example.bancot.data.remote.model.AccountDto
import com.example.bancot.data.remote.model.PaymentDto
import retrofit2.http.GET

interface AccountApiService {

    @GET("Login")
    suspend fun getInfoAccount() : List<AccountDto>

    @GET("payments")
    suspend fun getPayments() : List<PaymentDto>

}