package com.example.bancot.models.routNav

sealed class RoutNavigation(
    val rout :  String
){
    object HomeScreen : RoutNavigation("home_screen")
    object PaymentsScreen : RoutNavigation("payments_screen")
}