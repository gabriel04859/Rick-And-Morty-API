package com.gabriel.rickandmortyapi.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gabriel.rickandmortyapi.data.model.Character

@Database(entities = [Character::class], version = 1)
abstract class CharacterDatabase : RoomDatabase(){
    abstract val characterDAO : CharacterDAO
    companion object{
        @Volatile
        private var INSTANCE : CharacterDatabase? = null

        fun getInstance(context : Context): CharacterDatabase {
            synchronized(this){
                var instance : CharacterDatabase? = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(context, CharacterDatabase::class.java, "character_db").build()
                }
                return instance
            }
        }
    }
}