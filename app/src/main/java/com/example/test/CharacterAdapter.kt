package com.example.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test.model.Character

class CharacterAdapter(private val characterList: List<Character>,private val itemClickListener: CharacterViewHolder.OnItemClickListener) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        // Inflate the item layout for each item
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(itemView,itemClickListener)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        // Get the current character and set its data to the views
        val character = characterList[position]

        holder.nameTextView.text = character.name
        Glide.with(holder.itemView.context)
            .load(character.imageUrl)
            .into(holder.imageView)

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(character) // Trigger the click event
        }
    }

    override fun getItemCount(): Int = characterList.size

    // ViewHolder that holds the references to the views
    class CharacterViewHolder(itemView: View, clickListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val imageView: ImageView = itemView.findViewById(R.id.imageView)

        interface OnItemClickListener {
            fun onItemClick(character: Character)
        }

    }


}
