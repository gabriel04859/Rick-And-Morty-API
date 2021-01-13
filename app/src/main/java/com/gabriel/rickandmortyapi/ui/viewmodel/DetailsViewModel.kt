package com.gabriel.rickandmortyapi.ui.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.gabriel.rickandmortyapi.data.model.Character
import com.gabriel.rickandmortyapi.repository.DetailsRepository
import com.gabriel.rickandmortyapi.utils.Constants.Companion.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Suppress("UNCHECKED_CAST")
class DetailsViewModel(private val detailsRepository: DetailsRepository) : ViewModel() {
    private var _isSaved : Boolean = false
    var isSaved = MutableLiveData<Boolean>()



    fun saveCharacter(character: Character){
        viewModelScope.launch(Dispatchers.IO){
            try {
                detailsRepository.insertCharacter(character)
                withContext(Dispatchers.Main){
                    Log.i(TAG,"Personagem salvo com sucesso")
                    isSaved.value = true
                }

            }catch (e : Exception){
                Log.i(TAG,"Erro ao salvar personagem: ${e.message}")
                isSaved.value = false
            }
        }

    }

    class DetailsViewModelFactory(private val detailsRepository: DetailsRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DetailsViewModel(detailsRepository) as T
        }
    }
}

