package meh.daniel.com.saymynameapp.presentation.screen.characterlist

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

class HeroAdapter(
    private val onClickHero: (hero: CharacterUI.Character) -> Unit,
    private val onClickButton: () -> Unit
) : ListAdapter<CharacterUI, RecyclerView.ViewHolder>(HeroUIDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when(viewType) {
        R.layout.item_character -> HeroViewHolder.from(parent, onClickHero)
        R.layout.item_header -> HeaderViewHolder.from(parent)
        R.layout.item_button_next -> ButtonNextViewHolder.from(parent, onClickButton)
        else -> throw Throwable("onCreateViewHolder exception - unknown view type by name: $viewType")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = when(holder) {
        is HeroViewHolder -> holder.bind(item = getItem(position) as CharacterUI.Character)
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

class HeroViewHolder(
    private val binding: ItemCharacterBinding,
    private val onClickItem: (hero: CharacterUI.Character) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: CharacterUI.Character){
        with(binding){
            nameHero.text = item.name
            Glide.with(photoHero)
                .load(item.image)
                .into(photoHero)
            setListener(item)
            birthdayHero.text = item.birthdate
        }
    }
    private fun setListener(item: CharacterUI.Character) {
        binding.itemHeroRoot.setOnClickListener{
            onClickItem(item)
        }
    }
    companion object {
        fun from(parent: ViewGroup, onClickItem: (hero: CharacterUI.Character) -> Unit): RecyclerView.ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemCharacterBinding.inflate(layoutInflater, parent, false)
            return HeroViewHolder(binding, onClickItem)
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
        fun from(parent: ViewGroup): RecyclerView.ViewHolder {
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
        fun from(parent: ViewGroup, onClickButton: () -> Unit): RecyclerView.ViewHolder {
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