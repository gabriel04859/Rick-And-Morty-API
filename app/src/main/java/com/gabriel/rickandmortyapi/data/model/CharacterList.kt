package com.gabriel.rickandmortyapi.data.model

import com.google.gson.annotations.SerializedName

data class CharacterList(
    @SerializedName("results")
    val characters : List<Character>? = null
) {
}