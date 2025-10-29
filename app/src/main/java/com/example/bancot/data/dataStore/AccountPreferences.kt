package com.example.bancot.data.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.bancot.models.classes.Account
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.accountDataStore: DataStore<Preferences> by preferencesDataStore(name = "account_prefs")

class AccountPreferences(private val context: Context){

    companion object{
        val ACCOUNT_NAME_KEY = stringPreferencesKey("account_name")
        val ACCOUNT_NUMBER_KEY = stringPreferencesKey("account_number")
        val BRANCH_NUMBER_KEY = stringPreferencesKey("branch_number")
        val BALANCE_KEY = doublePreferencesKey("balance")
        val ACCOUNT_ID_KEY = stringPreferencesKey("account_id")

    }

    val customerName: Flow<String?> = context.accountDataStore.data.map { it[ACCOUNT_NAME_KEY] }
    val accountNumber: Flow<String?> = context.accountDataStore.data.map { it[ACCOUNT_NUMBER_KEY] }
    val branchNumber: Flow<String?> = context.accountDataStore.data.map { it[BRANCH_NUMBER_KEY] }
    val accountBalance: Flow<Double?> = context.accountDataStore.data.map { preferences ->preferences[BALANCE_KEY]}
    val id: Flow<String?> = context.accountDataStore.data.map { it[ACCOUNT_ID_KEY] }

    suspend fun saveAccountInfo(account: Account) {
        context.accountDataStore.edit { preferences ->
            preferences[ACCOUNT_NAME_KEY] = account.customerName
            preferences[ACCOUNT_NUMBER_KEY] = account.accountNumber
            preferences[BRANCH_NUMBER_KEY] = account.branchNumber
            preferences[BALANCE_KEY] = account.checkingAccountBalance
            preferences[ACCOUNT_ID_KEY] = account.id
        }
    }
}