package meh.daniel.com.hero_list_feature

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import meh.daniel.com.hero_list_feature.databinding.ItemHeroBinding

class HeroAdapter : ListAdapter<String, RecyclerView.ViewHolder>(HeroDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when(viewType) {
        R.layout.fragment_herolist -> HeroViewHolder.from(parent)
        else -> throw Throwable("onCreateViewHolder exception - unknown view type by name: $viewType")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = when(holder) {
        is HeroViewHolder -> holder.bind(item = getItem(position))
        else -> throw Throwable("onBindViewHolder exception - unknown holder of view by name ${holder.itemView.id}")
    }

    override fun getItemViewType(position: Int): Int = when(getItem(position)) {
        is String -> R.layout.fragment_herolist
        else -> throw Exception("getItemViewType unknown item class exception from position: $position")
    }
}

class HeroViewHolder(private val binding: ItemHeroBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: String){
        binding.name.text = item
    }
    companion object {
        fun from(parent: ViewGroup): RecyclerView.ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemHeroBinding.inflate(layoutInflater, parent, false)
            return HeroViewHolder(binding)
        }
    }
}

class HeroDiffUtil: DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
}