package com.example.disneycharacters.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.disneycharacters.domain.model.Data
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

    private val _characterDetails = MutableStateFlow(listOf<Data>())
    val characterDetails : StateFlow<List<Data>> get() = _characterDetails


    fun fetchCharacterDetails(characterName : String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = characterRepository.getCharacterDetails(characterName)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful && response.body() != null) {
                        _characterDetails.value = response.body()!!.data
                    }
                }
            } catch (e: Exception) {
                _characterDetails.value = emptyList()
                Log.e("Exception : ", e.toString())

            }
        }
    }

}