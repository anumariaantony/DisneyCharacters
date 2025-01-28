package com.example.disneycharacters.di

import com.example.disneycharacters.data.remote.CharacterApi
import com.example.disneycharacters.data.repository.CharacterRepositoryImpl
import com.example.disneycharacters.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCharacterApi() : CharacterApi {
        return Retrofit.Builder()
            .baseUrl("https://api.disneyapi.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharacterApi::class.java)

    }

    @Provides
    @Singleton
    fun provideCharacterRepository(api: CharacterApi) : CharacterRepository {
        return CharacterRepositoryImpl(api)
    }
}