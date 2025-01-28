package com.example.disneycharacters.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.disneycharacters.domain.model.Data
import com.example.disneycharacters.presentation.viewmodel.CharacterViewModel

@Composable
fun CharacterDetailsScreen(characterViewModel: CharacterViewModel) {

    val characterDataList by characterViewModel.characterDetails.collectAsState()

    Column {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .weight(1f)
        ) {
            if (characterDataList.isNotEmpty()) {
                items(characterDataList) {
                    CharacterList(it)
                }
            }
        }
    }
}

@Composable
fun ShowErrorText() {
    Column (
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("The specified character details is not available. Please try another character",
            style = MaterialTheme.typography.bodyMedium)
    }

}

@Composable
fun CharacterList(characterDataItem: Data) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Row {
            CharacterImage(characterDataItem)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = characterDataItem.name, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
fun CharacterImage(characterDataItem: Data) {
    Image(
        painter = rememberAsyncImagePainter(characterDataItem.imageUrl),
        contentDescription = characterDataItem.name,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
    )
}
