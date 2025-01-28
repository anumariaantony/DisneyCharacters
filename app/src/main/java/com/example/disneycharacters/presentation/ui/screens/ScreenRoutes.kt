package com.example.disneycharacters.presentation.ui.screens

sealed class Screen(val route: String) {
    data object Search : Screen("search")
    data object Character : Screen("character")
}