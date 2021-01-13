package com.gabriel.rickandmortyapi.database

import androidx.room.Dao
import androidx.room.Insert
import com.gabriel.rickandmortyapi.data.model.Character

@Dao
interface CharacterDAO {
    @Insert
    suspend fun insert(character : Character)
}