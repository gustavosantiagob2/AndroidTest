package com.example.bancot.models.routNav

sealed class RoutNavigation(
    val rout :  String
){
    object homeScreen : RoutNavigation("home_screen")
    object paymentsScreen : RoutNavigation("payments_screen")
}