package com.dialiax.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
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
        enableEdgeToEdge()
        val viewModel: NewsViewModel by viewModels()
        setContent {
            NewsAppTheme {
                // Create a NavController
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        if (viewModel.res.value == null) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        } else {
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
    }
}