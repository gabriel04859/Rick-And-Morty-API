package com.gabriel.rickandmortyapi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gabriel.rickandmortyapi.R
import com.gabriel.rickandmortyapi.data.model.Character
import com.squareup.picasso.Picasso

class FeedAdapter(private val context : Context, private val characterClickListener : OnCharacterClickListener) : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>(){
    private var characterList : List<Character> = ArrayList()

    interface OnCharacterClickListener{
        fun onCharacterClick(character: Character)

    }
    fun setCharacters(characters : List<Character>){
        characterList = characters
    }

    class FeedViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val imageViewFeedItem : ImageView = itemView.findViewById(R.id.imageViewFeedItem)
        val textViewNameFeedItem : TextView = itemView.findViewById(R.id.textViewNameFeedItem)
        val textViewStatusFeedItem : TextView = itemView.findViewById(R.id.textViewStatusFeedItem)
        val textViewGenderFeedItem : TextView = itemView.findViewById(R.id.textViewGenderFeedItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false)
        return FeedViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val character = characterList[position]
        character.apply {
            Picasso.with(context).load(image).into(holder.imageViewFeedItem)
            holder.textViewNameFeedItem.text = name
            holder.textViewStatusFeedItem.text = status
            holder.textViewGenderFeedItem.text = gender
        }
        holder.itemView.setOnClickListener {
            characterClickListener.onCharacterClick(character)
        }
    }

    override fun getItemCount() = characterList.size


}