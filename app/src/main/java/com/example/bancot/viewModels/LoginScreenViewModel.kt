package com.example.bancot.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bancot.data.repository.AccountRepository
import com.example.bancot.viewModels.uiState.LoginScreenUiState
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginScreenViewModel(
    private val repository: AccountRepository,
) : ViewModel() {

    private val regex = Regex("^(?=.*[A-Z])(?=.*[0-9]).*")

    private var _uiState: MutableStateFlow<LoginScreenUiState> =
        MutableStateFlow(LoginScreenUiState())

    val uiState get() = _uiState.asStateFlow()

    private val _loginEvents = MutableSharedFlow<LoginScreenEvent>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val loginEvents: SharedFlow<LoginScreenEvent> = _loginEvents.asSharedFlow()

    private fun validEmail(email: String): Boolean {
        if (email.contains("@") && email.isNotBlank()) {
            return true
        } else if (email.isBlank()) {
            return false
        } else {
            return false
        }
    }

    private fun validPassword(password: String): Boolean {
        return password.length >= 6 && password.matches(regex)
    }

    fun onEmailChange(email: String) {
        _uiState.update { currentState ->
            currentState.copy(
                email = email,
                errorEmail = !validEmail(email = email),
                butonEnabled = validEmail(email = email) && validPassword(currentState.password)
            )
        }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { currentState ->
            currentState.copy(
                password = password,
                errorPassword = !validPassword(password = password),
                butonEnabled = validPassword(password = password) && validEmail(email = currentState.email)
            )
        }
    }

    fun onLoginClick() {
        viewModelScope.launch {
            _uiState.update { it.copy( isLoading = true, apiErrorMessage = null ) }

            try {
                _loginEvents.emit(LoginScreenEvent.NavigateToPayments)
            }catch (e: Exception){
                val errorMessage = "Falha ao realizar login. Verifique suas credenciais."

                println("Error na API: ${e}")
                _uiState.update {
                    it.copy(
                        apiErrorMessage = errorMessage,
                        isLoading = false
                    )
                }
            }finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }
}
