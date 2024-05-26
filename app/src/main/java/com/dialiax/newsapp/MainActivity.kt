package com.dialiax.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dialiax.newsapp.Screen.HomeApp
import com.dialiax.newsapp.Screen.NewsDetailScreen
import com.dialiax.newsapp.Screen.NewsViewModel
import com.dialiax.newsapp.ui.theme.NewsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: NewsViewModel by viewModels()
        setContent {
            NewsAppTheme {
                // Create a NavController
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Setup the NavHost
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") { HomeApp(viewModel = viewModel, navController = navController) }
                        composable("detail/{title}") { backStackEntry ->
                            val article = viewModel.res.value?.articles?.find { it.title == backStackEntry.arguments?.getString("title") }
                            if (article != null) {
                                NewsDetailScreen(news = article)
                            }
                        }
                    }
                }
            }
        }
    }
}