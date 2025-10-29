package com.example.bancot.viewModels

sealed interface LoginScreenEvent {
    data object NavigateToPayments : LoginScreenEvent

    data class ShowSnackbar(val message: String) : LoginScreenEvent

}