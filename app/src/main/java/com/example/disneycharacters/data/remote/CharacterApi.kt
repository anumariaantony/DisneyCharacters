package com.example.disneycharacters.data.remote

import com.example.disneycharacters.domain.model.DisneyCharacter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {

    @GET("character?")
    suspend fun getCharacterDetail(@Query("name") characterName : String) : Response<DisneyCharacter>
}