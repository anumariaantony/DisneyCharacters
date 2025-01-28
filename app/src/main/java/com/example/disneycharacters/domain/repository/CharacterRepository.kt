package com.example.disneycharacters.domain.repository

import com.example.disneycharacters.domain.model.Character
import retrofit2.Response

interface CharacterRepository {
    suspend fun getCharacterDetails(characterName : String) : Response<Character>
}