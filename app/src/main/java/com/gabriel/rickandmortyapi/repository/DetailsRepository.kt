package com.gabriel.rickandmortyapi.repository

import com.gabriel.rickandmortyapi.data.model.Character
import com.gabriel.rickandmortyapi.database.CharacterDAO

class DetailsRepository(private val characterDAO: CharacterDAO) {
    suspend fun insertCharacter(character: Character){
        characterDAO.insert(character)

    }

}