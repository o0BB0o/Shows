package com.example.shows

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import coil.compose.rememberAsyncImagePainter
import com.example.shows.data.ApiService
import com.example.shows.data.Show
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val shows = mutableStateListOf<Show>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch(Dispatchers.IO) {
            val fetchedShows = ApiService.getShow()
            shows.addAll(fetchedShows)
        }
        setContent {
            ShowList(shows)
        }
    }
}

@Composable
fun ShowList(shows: List<Show>) {
    val coroutineScope = rememberCoroutineScope()
    val selectedShow = remember { mutableStateOf<Show?>(null) }

    LazyColumn {
        items(shows) { show ->
            ListItem(show = show, onItemClick = { clickedShow ->
                selectedShow.value = clickedShow
            })
        }
    }
    
    if (selectedShow.value != null) {
        AlertDialog(
            onDismissRequest = { selectedShow.value = null },
            title = { Text(text = selectedShow.value?.title ?: "") },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(selectedShow.value?.logo ?: ""),
                        contentDescription = "Logo",
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "DateTime:")
                    Text(text = selectedShow.value?.dateTime ?: "To Be Announced")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Location:")
                    Text(text = selectedShow.value?.location ?: "To Be Announced")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Artists:")
                    selectedShow.value?.artists?.forEach { artist ->
                        if(artist.name.isNotEmpty()) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(vertical = 4.dp)
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(artist.photo),
                                    contentDescription = "Artist Photo",
                                    modifier = Modifier.size(40.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = artist.name)
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(onClick = { selectedShow.value = null }) {
                    Text(text = "Close")
                }
            }
        )
    }
}