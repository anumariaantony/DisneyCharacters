package com.example.disneycharacters.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.disneycharacters.presentation.ui.screens.CharacterDetailsScreen
import com.example.disneycharacters.presentation.ui.screens.Screen
import com.example.disneycharacters.presentation.ui.screens.SearchScreen
import com.example.disneycharacters.presentation.ui.theme.DisneyCharactersTheme
import com.example.disneycharacters.presentation.viewmodel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DisneyCharactersTheme() {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    DisneyCharacterSearchApp()
                }
            }
        }
    }
}

@Composable
fun DisneyCharacterSearchApp() {
    val navController = rememberNavController()
    val characterViewModel = hiltViewModel<CharacterViewModel>()
    NavHost(navController = navController, startDestination = Screen.Search.toString()) {
        composable(Screen.Search.route) {
            SearchScreen(onSearchButtonClicked = {
                navController.navigate(Screen.Character.toString())
            }, characterViewModel)
        }
        composable(Screen.Character.route) {
            CharacterDetailsScreen(characterViewModel)
        }
    }
}
