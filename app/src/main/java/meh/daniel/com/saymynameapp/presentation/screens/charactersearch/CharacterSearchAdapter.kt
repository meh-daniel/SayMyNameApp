package meh.daniel.com.saymynameapp.presentation.screens.charactersearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import meh.daniel.com.saymynameapp.R
import meh.daniel.com.saymynameapp.databinding.ItemCharacterBinding
import meh.daniel.com.saymynameapp.presentation.model.CharacterUI

class CharacterSearchAdapter(
    private val onClickCharacter: (character: CharacterUI.Character) -> Unit
) : ListAdapter<CharacterUI, RecyclerView.ViewHolder>(HeroUIDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when(viewType) {
        R.layout.item_character -> CharacterViewHolder.onCreateViewHolder(parent, onClickCharacter)
        else -> throw Throwable("onCreateViewHolder exception - unknown view type by name: $viewType")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = when(holder) {
        is CharacterViewHolder -> holder.bind(item = getItem(position) as CharacterUI.Character)
        else -> throw Throwable("onBindViewHolder exception - unknown holder of view by name ${holder.itemView.id}")
    }

    override fun getItemViewType(position: Int): Int = when(getItem(position)) {
        is CharacterUI.Character -> R.layout.item_character
        else -> throw Exception("getItemViewType unknown item class exception from position: $position")
    }
}

class CharacterViewHolder(
    private val binding: ItemCharacterBinding,
    private val onClickItem: (hero: CharacterUI.Character) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CharacterUI.Character){
        with(binding){
            nameCharacter.text = item.name
            Glide.with(photoCharacter)
                .load(item.image)
                .into(photoCharacter)
            setListener(item)
            birthdayCharacter.text = item.birthdate
        }
    }
    private fun setListener(item: CharacterUI.Character) {
        binding.itemHeroRoot.setOnClickListener{
            onClickItem(item)
        }
    }
    companion object {
        fun onCreateViewHolder(parent: ViewGroup, onClickItem: (character: CharacterUI.Character) -> Unit): RecyclerView.ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemCharacterBinding.inflate(layoutInflater, parent, false)
            return CharacterViewHolder(binding, onClickItem)
        }
    }
}

class HeroUIDiffUtil: DiffUtil.ItemCallback<CharacterUI>() {
    override fun areItemsTheSame(oldItem: CharacterUI, newItem: CharacterUI): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: CharacterUI, newItem: CharacterUI): Boolean = oldItem == newItem
}