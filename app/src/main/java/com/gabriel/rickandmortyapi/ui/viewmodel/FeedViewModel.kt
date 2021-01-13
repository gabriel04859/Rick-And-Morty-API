package com.gabriel.rickandmortyapi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.gabriel.rickandmortyapi.data.model.Character
import com.gabriel.rickandmortyapi.repository.FeedRepository
import com.gabriel.rickandmortyapi.utils.Constants.Companion.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Suppress("UNCHECKED_CAST")
class FeedViewModel(private val feedRepository: FeedRepository) : ViewModel() {
    private var _characterList = MutableLiveData<List<Character>>()
    val characterList : LiveData<List<Character>> get() = _characterList

    fun getAllCharacters(){
        viewModelScope.launch (Dispatchers.IO){
            val result = feedRepository.getAllCharacters()
            if (result.isSuccessful){
                withContext(Dispatchers.Main){
                    result.body()?.let { characters ->
                        Log.i(TAG,"Lista de characters: ${characters.characters}")
                        _characterList.value = characters.characters

                    }
                }
            }else{
                Log.i(TAG,"Erro ao obter os Characters")
            }
        }
    }

    fun getCharacterByName(name : String){
        viewModelScope.launch(Dispatchers.IO){
            val result = feedRepository.getCharacterByName(name)
            if (result.isSuccessful){
                withContext(Dispatchers.Main){
                    result.body()?.let {
                        Log.i(TAG,"Personagens pelo o nome: ${it.characters}")
                        _characterList.value = it.characters
                    }
                }
            }
        }
    }

    class FeedViewModelFactory(private val feedRepository: FeedRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return FeedViewModel(feedRepository) as T
        }
    }


}