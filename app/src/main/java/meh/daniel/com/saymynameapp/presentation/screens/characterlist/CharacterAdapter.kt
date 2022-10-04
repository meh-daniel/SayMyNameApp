package meh.daniel.com.saymynameapp.presentation.screens.characterlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import meh.daniel.com.saymynameapp.R
import meh.daniel.com.saymynameapp.databinding.ItemButtonNextBinding
import meh.daniel.com.saymynameapp.databinding.ItemCharacterBinding
import meh.daniel.com.saymynameapp.databinding.ItemHeaderBinding
import meh.daniel.com.saymynameapp.presentation.model.CharacterUI

class CharacterAdapter(
    private val onClickCharacter: (hero: CharacterUI.Character) -> Unit,
    private val onClickButton: () -> Unit
) : ListAdapter<CharacterUI, RecyclerView.ViewHolder>(HeroUIDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when(viewType) {
        R.layout.item_character -> CharacterViewHolder.onCreateViewHolder(parent, onClickCharacter)
        R.layout.item_header -> HeaderViewHolder.onCreateViewHolder(parent)
        R.layout.item_button_next -> ButtonNextViewHolder.onCreateViewHolder(parent, onClickButton)
        else -> throw Throwable("onCreateViewHolder exception - unknown view type by name: $viewType")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = when(holder) {
        is CharacterViewHolder -> holder.bind(item = getItem(position) as CharacterUI.Character)
        is HeaderViewHolder -> holder.bind(item = getItem(position) as CharacterUI.Header)
        is ButtonNextViewHolder -> holder.bind(item = getItem(position) as CharacterUI.Button)
        else -> throw Throwable("onBindViewHolder exception - unknown holder of view by name ${holder.itemView.id}")
    }

    override fun getItemViewType(position: Int): Int = when(getItem(position)) {
        is CharacterUI.Character -> R.layout.item_character
        is CharacterUI.Header -> R.layout.item_header
        is CharacterUI.Button -> R.layout.item_button_next
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
        fun onCreateViewHolder(parent: ViewGroup, onClickItem: (hero: CharacterUI.Character) -> Unit): RecyclerView.ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemCharacterBinding.inflate(layoutInflater, parent, false)
            return CharacterViewHolder(binding, onClickItem)
        }
    }
}

class HeaderViewHolder(private val binding: ItemHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CharacterUI.Header) {
        with(binding){
            headerTitle.text = item.title
        }
    }
    companion object {
        fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemHeaderBinding.inflate(layoutInflater, parent, false)
            return HeaderViewHolder(binding)
        }
    }
}

class ButtonNextViewHolder(
    private val binding: ItemButtonNextBinding,
    private val onClickButton: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CharacterUI.Button){
        setListener()
    }
    private fun setListener() {
        binding.nextBtn.setOnClickListener {
            onClickButton()
        }
    }
    companion object {
        fun onCreateViewHolder(parent: ViewGroup, onClickButton: () -> Unit): RecyclerView.ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemButtonNextBinding.inflate(layoutInflater, parent, false)
            return ButtonNextViewHolder(binding, onClickButton)
        }
    }
}

class HeroUIDiffUtil: DiffUtil.ItemCallback<CharacterUI>() {
    override fun areItemsTheSame(oldItem: CharacterUI, newItem: CharacterUI): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: CharacterUI, newItem: CharacterUI): Boolean = oldItem == newItem
}