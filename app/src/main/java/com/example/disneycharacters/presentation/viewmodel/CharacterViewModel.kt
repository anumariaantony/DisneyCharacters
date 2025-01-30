package com.example.disneycharacters.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disneycharacters.domain.model.CharacterState
import com.example.disneycharacters.domain.model.DisneyCharacterItem
import com.example.disneycharacters.domain.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor (private val characterRepository : CharacterRepository)
    : ViewModel() {

    private val _characterDetails = MutableStateFlow(listOf<DisneyCharacterItem>())
    val characterDetails : StateFlow<List<DisneyCharacterItem>> get() = _characterDetails

    private val _characterState = MutableStateFlow<CharacterState>(CharacterState.Loading)
    val characterState :StateFlow<CharacterState> get() = _characterState

    fun fetchCharacterDetails(characterName : String) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                _characterState.value = CharacterState.Loading
                val response = characterRepository.getCharacterDetails(characterName)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null && response.body()!!.data.isNotEmpty()) {
                        _characterDetails.value = response.body()!!.data
                        _characterState.value = CharacterState.Success(_characterDetails.value)
                    } else {
                        _characterState.value = CharacterState.Error("Error")
                    }
                }
            } catch (e: Exception ) {
                _characterState.value = CharacterState.Error(e.toString())
                 Log.e("Error: ", e.toString())
            }
        }
    }

}