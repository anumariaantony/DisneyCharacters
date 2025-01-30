package com.example.disneycharacters.domain.repository

import com.example.disneycharacters.domain.model.DisneyCharacter
import retrofit2.Response

interface CharacterRepository {
    suspend fun getCharacterDetails(characterName : String) : Response<DisneyCharacter>
}