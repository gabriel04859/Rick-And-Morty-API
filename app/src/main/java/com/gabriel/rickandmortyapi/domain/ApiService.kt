package com.gabriel.rickandmortyapi.domain

import com.gabriel.rickandmortyapi.data.model.CharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun getAllCharacters() : Response<CharacterList>

    @GET("character")
    suspend fun getCharacterByName(@Query("name") name : String) : Response<CharacterList>

}