package com.mustafin.ebooks.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mustafin.ebooks.mainFlow.ui.screens.homeScreen.HomeScreenView
import kotlinx.serialization.Serializable

// Экраны приложения для Type Safe Navigation
@Serializable object HomeScreen
@Serializable data class ReaderScreen(val bookId: Int)

// Граф навигации
@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HomeScreen){
        composable<HomeScreen> {
            HomeScreenView()
        }
    }
}