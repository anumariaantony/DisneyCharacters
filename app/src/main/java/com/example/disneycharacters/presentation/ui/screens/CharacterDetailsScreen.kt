package com.example.disneycharacters.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.disneycharacters.R
import com.example.disneycharacters.domain.model.CharacterState
import com.example.disneycharacters.domain.model.DisneyCharacterItem
import com.example.disneycharacters.presentation.viewmodel.CharacterViewModel

@Composable
fun CharacterDetailsScreen(characterViewModel: CharacterViewModel) {

    val characterDataList by characterViewModel.characterDetails.collectAsState()
    val characterState = characterViewModel.characterState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when(characterState.value) {
            is CharacterState.Loading -> {
                CircularProgressIndicator()
            }
            is CharacterState.Success -> {
                ShowCharacterList(characterDataList)
            }
            is CharacterState.Error -> {
                ShowErrorText()
            }
        }
    }
}

@Composable
fun ShowCharacterList(characterDataList: List<DisneyCharacterItem>) {
    Column {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .weight(1f)
        ) {
            items(characterDataList) {
                CharacterList(it)
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
        Text(
            stringResource(R.string.no_character_match),
            style = MaterialTheme.typography.bodyLarge
        )
    }

}

@Composable
fun CharacterList(characterDataItem: DisneyCharacterItem) {
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
fun CharacterImage(characterDataItem: DisneyCharacterItem) {
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
