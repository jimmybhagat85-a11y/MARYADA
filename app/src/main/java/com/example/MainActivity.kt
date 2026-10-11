package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.AppScreen
import com.example.ui.MaryadaViewModel
import com.example.ui.screens.CategoriesScreen
import com.example.ui.screens.HologramLabScreen
import com.example.ui.screens.QuestionScreen
import com.example.ui.screens.ReflectionsScreen
import com.example.ui.screens.ResultScreen
import com.example.ui.screens.TwentyQuestionsScreen
import com.example.ui.screens.WelcomeScreen
import com.example.ui.screens.WisdomExplorerScreen
import com.example.ui.theme.DeepBlack
import com.example.ui.theme.MaryadaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaryadaTheme {
                MaryadaApp()
            }
        }
    }
}

@Composable
fun MaryadaApp(viewModel: MaryadaViewModel = viewModel()) {
    val currentScreen by viewModel.currentScreen.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing,
        containerColor = DeepBlack
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(DeepBlack)
        ) {
            when (currentScreen) {
                AppScreen.WELCOME -> WelcomeScreen(viewModel = viewModel)
                AppScreen.TWENTY_QUESTIONS -> TwentyQuestionsScreen(viewModel = viewModel)
                AppScreen.SINGLE_QUESTION -> QuestionScreen(viewModel = viewModel)
                AppScreen.RESULT -> ResultScreen(viewModel = viewModel)
                AppScreen.HOLOGRAM_LAB -> HologramLabScreen(viewModel = viewModel)
                AppScreen.SAVED_REFLECTIONS -> ReflectionsScreen(viewModel = viewModel)
                AppScreen.WISDOM_EXPLORER -> WisdomExplorerScreen(viewModel = viewModel)
                AppScreen.CATEGORIES -> CategoriesScreen(viewModel = viewModel)
            }
        }
    }
}
