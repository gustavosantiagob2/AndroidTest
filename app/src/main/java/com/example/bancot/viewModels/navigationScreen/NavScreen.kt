package com.example.bancot.viewModels.navigationScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bancot.MyApplication
import com.example.bancot.models.routNav.RoutNavigation
import com.example.bancot.viewModels.LoginScreenViewModel
import com.example.bancot.viewModels.LoginScreenViewModelFactory
import com.example.bancot.viewModels.PaymentScreenViewModel
import com.example.bancot.viewModels.PaymentsScreenViewModelFactory
import com.example.bancot.views.screens.HomeScreen
import com.example.bancot.views.screens.PaymentsScreen

@Composable
fun getAppContainer(): MyApplication {
    return LocalContext.current.applicationContext as MyApplication
}

@Composable
fun AppNavGraph(navController: NavHostController) {

    val appContainer = getAppContainer()
    val accountRepository = appContainer.accountRepository

    val loginViewModelFactory   = remember { LoginScreenViewModelFactory(repository = accountRepository) }
    val paymentViewModelFactory = remember { PaymentsScreenViewModelFactory(repository = accountRepository) }


    NavHost(
        navController = navController,
        startDestination = RoutNavigation.homeScreen.rout
    ) {
        composable(route = RoutNavigation.homeScreen.rout) {
            val viewModel: LoginScreenViewModel = viewModel(factory = loginViewModelFactory)
            HomeScreen(viewModel = viewModel, navController = navController)
        }
        composable(route = RoutNavigation.paymentsScreen.rout) {
            val viewModel: PaymentScreenViewModel = viewModel(factory = paymentViewModelFactory)
            PaymentsScreen(viewModel = viewModel, navController = navController)
        }
    }
}
