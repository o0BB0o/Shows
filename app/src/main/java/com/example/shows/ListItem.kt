package com.example.shows

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.shows.data.Show
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.ui.draw.clip
import coil.compose.rememberAsyncImagePainter

@Composable
fun ListItem(show: Show, onItemClick: (Show) -> Unit) {
    Card(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp).fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(8.dp))

    ) {
        Row(Modifier.clickable { onItemClick(show)} ) {
            Image(
                painter = rememberAsyncImagePainter(model = show.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(80.dp).clip(shape = RoundedCornerShape(corner = CornerSize(8.dp)))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)) {
                Text(text = show.title, style = typography.h6)
            }
        }
    }
}