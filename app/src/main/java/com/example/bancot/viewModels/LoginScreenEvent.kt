package com.example.bancot.viewModels

sealed interface LoginScreenEvent {
    data object NavigateToPayments : LoginScreenEvent
}