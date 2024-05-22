package com.dialiax.newsapp.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dialiax.newsapp.network.Article

@Composable
fun NewsDetailScreen(news: Article) {
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
        Text(text = news.content ?: "", style = MaterialTheme.typography.bodyMedium)
    }
}