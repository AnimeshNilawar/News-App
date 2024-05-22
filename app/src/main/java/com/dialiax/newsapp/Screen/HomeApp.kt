package com.dialiax.newsapp.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.dialiax.newsapp.R
import com.dialiax.newsapp.network.Article
import com.dialiax.newsapp.network.NewsModel


@Composable
fun HomeApp(viewModel: NewsViewModel, navController: NavController) {
    val newsList =
        viewModel.res.value?.articles ?: listOf() // Provide an empty list as a default value

    if (viewModel.isLoading.value) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Column {
            CustomAppBar(viewModel)

            LazyColumn {
                items(newsList) { news ->
                    NewsCard(news = news, navController = navController)
                    Spacer(modifier = Modifier.height(8.dp)) // Add some space between news cards
                }
            }
        }
    }
}

@Composable
fun NewsCard(news: Article, navController: NavController) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.padding(15.dp, 0.dp, 15.dp, 0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = news.title ?: "", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            AsyncImage(
                model = news.urlToImage ?: "",
                contentDescription = "News Image",
                modifier = Modifier.size(400.dp, 200.dp),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(onClick = { navController.navigate("detail/${news.title}") }) {
                    Text(text = "Read More")

                }
            }
        }
    }
    Spacer(modifier = Modifier.height(4.dp))
}


@Composable
fun CustomAppBar(viewModel: NewsViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon),
            contentDescription = "News App",
            modifier = Modifier.size(70.dp)
        )

        IconButton(onClick = { viewModel.refreshNews() }) {
            Icon(Icons.Filled.Refresh, contentDescription = null)
        }
    }
}