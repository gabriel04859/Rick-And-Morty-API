package com.gabriel.rickandmortyapi.repository

import com.gabriel.rickandmortyapi.data.model.CharacterList
import com.gabriel.rickandmortyapi.domain.RetrofitInstance
import retrofit2.Response

class FeedRepository {
    suspend fun getAllCharacters() : Response<CharacterList> = RetrofitInstance.apiService.getAllCharacters()

    suspend fun getCharacterByName(name : String) : Response<CharacterList> = RetrofitInstance.apiService.getCharacterByName(name)


}