package com.example.disneycharacters.data.repository

import com.example.disneycharacters.data.remote.CharacterApi
import com.example.disneycharacters.domain.model.Character
import com.example.disneycharacters.domain.repository.CharacterRepository
import retrofit2.Response

class CharacterRepositoryImpl(private val api : CharacterApi) : CharacterRepository {
    override suspend fun getCharacterDetails(characterName: String): Response<Character> {
        return api.getCharacterDetail(characterName)
    }
}