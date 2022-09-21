package meh.daniel.com.feature_main.herosearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import meh.daniel.com.feature_main.R
import meh.daniel.com.feature_main.databinding.ItemHeroBinding

class HeroAdapter(
    private val onClickHero: (hero: HeroUI.Hero) -> Unit
) : ListAdapter<HeroUI, RecyclerView.ViewHolder>(HeroUIDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when(viewType) {
        R.layout.item_hero -> HeroViewHolder.from(parent, onClickHero)
        else -> throw Throwable("onCreateViewHolder exception - unknown view type by name: $viewType")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = when(holder) {
        is HeroViewHolder -> holder.bind(item = getItem(position) as HeroUI.Hero)
        else -> throw Throwable("onBindViewHolder exception - unknown holder of view by name ${holder.itemView.id}")
    }

    override fun getItemViewType(position: Int): Int = when(getItem(position)) {
        is HeroUI.Hero -> R.layout.item_hero
        else -> throw Exception("getItemViewType unknown item class exception from position: $position")
    }
}

class HeroViewHolder(
    private val binding: ItemHeroBinding,
    private val onClickItem: (hero: HeroUI.Hero) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: HeroUI.Hero){
        with(binding){
            nameHero.text = item.name
            Glide.with(photoHero)
                .load(item.image)
                .into(photoHero)
            setListener(item)
            birthdayHero.text = item.birthdate
        }
    }
    private fun setListener(item: HeroUI.Hero) {
        binding.itemHeroRoot.setOnClickListener{
            onClickItem(item)
        }
    }
    companion object {
        fun from(parent: ViewGroup, onClickItem: (hero: HeroUI.Hero) -> Unit): RecyclerView.ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemHeroBinding.inflate(layoutInflater, parent, false)
            return HeroViewHolder(binding, onClickItem)
        }
    }
}

class HeroUIDiffUtil: DiffUtil.ItemCallback<HeroUI>() {
    override fun areItemsTheSame(oldItem: HeroUI, newItem: HeroUI): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: HeroUI, newItem: HeroUI): Boolean = oldItem == newItem
}