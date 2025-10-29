package com.example.bancot.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bancot.data.repository.AccountRepository
import com.example.bancot.models.classes.Account
import com.example.bancot.viewModels.uiState.PaymentScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PaymentScreenViewModel(
    private val repository: AccountRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<PaymentScreenUiState> =
        MutableStateFlow(PaymentScreenUiState(isLoading = true))

    val uiState get() = _uiState.asStateFlow()

    // ⭐️ Expor o objeto Account (coletado do DataStore/Repository)
    val accountState: StateFlow<Account?> = repository.accountFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    init {
        payments()
    }

    private fun payments() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, apiErrorMessage = null) }
            try {
                kotlinx.coroutines.delay(5000) // Atraso de 2 segundos para fins de teste


                val paymentList = repository.getPayments()

                _uiState.update {
                    it.copy(
                        payments = paymentList,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        apiErrorMessage = "Falha ao carregar listas de contas pagas: ${e.message}"
                    )
                }
            }
        }
    }

}