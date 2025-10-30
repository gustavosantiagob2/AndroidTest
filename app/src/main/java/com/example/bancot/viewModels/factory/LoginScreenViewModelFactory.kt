package com.example.bancot.viewModels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bancot.data.repository.AccountRepository
import com.example.bancot.viewModels.LoginScreenViewModel

class LoginScreenViewModelFactory(
    private val repository: AccountRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginScreenViewModel::class.java)) {
            return LoginScreenViewModel(repository = repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}