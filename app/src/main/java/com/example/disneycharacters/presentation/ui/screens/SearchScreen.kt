package com.example.disneycharacters.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.disneycharacters.R
import com.example.disneycharacters.presentation.viewmodel.CharacterViewModel

@Composable
fun SearchScreen(onSearchButtonClicked: () -> Unit, characterViewModel: CharacterViewModel) {

    var characterName by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier.padding(5.dp),
            text = stringResource(R.string.app_name),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.padding(10.dp))

        OutlinedTextField(
            value = characterName,
            onValueChange = {characterName = it},
            label = {
                Text(stringResource(R.string.character_name))
            },
            singleLine = true
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Button(
            onClick =
            {
                characterViewModel.fetchCharacterDetails(characterName)
                onSearchButtonClicked()
            }

        ) {
            Text(stringResource(R.string.search))
        }
    }
}