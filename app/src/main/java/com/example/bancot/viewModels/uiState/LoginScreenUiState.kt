package com.example.bancot.viewModels.uiState

data class LoginScreenUiState(
    val email: String           = "",
    val password: String        = "",
    val butonEnabled: Boolean   = false,
    val errorEmail: Boolean     = false,
    val errorPassword: Boolean  = false,
    val isLoading: Boolean = false,
    val apiErrorMessage: String? = null,
)