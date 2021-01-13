package com.gabriel.rickandmortyapi.data.model
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tb_character")
data class Character(
        @PrimaryKey
        val id : Int? = null,
                     val name : String? = null,
                     val status : String? = null,
                     val species : String ? = null,
                     val gender : String = "",
                     val image : String ? = null) : Parcelable {
}

