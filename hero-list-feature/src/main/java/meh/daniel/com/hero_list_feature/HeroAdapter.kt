package meh.daniel.com.hero_list_feature

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import meh.daniel.com.hero_list_feature.databinding.ItemButtonBinding
import meh.daniel.com.hero_list_feature.databinding.ItemHeaderBinding
import meh.daniel.com.hero_list_feature.databinding.ItemHeroBinding

class HeroAdapter(
    private val onClickItem: (hero: HeroUI.Hero) -> Unit,
    private val onClickButton: () -> Unit
) : ListAdapter<HeroUI, RecyclerView.ViewHolder>(HeroUIDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when(viewType) {
        R.layout.item_hero -> HeroViewHolder.from(parent, onClickItem)
        R.layout.item_header -> HeaderViewHolder.from(parent)
        R.layout.item_button -> ButtonViewHolder.from(parent, onClickButton)
        else -> throw Throwable("onCreateViewHolder exception - unknown view type by name: $viewType")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = when(holder) {
        is HeroViewHolder -> holder.bind(item = getItem(position) as HeroUI.Hero)
        is HeaderViewHolder -> holder.bind(item = getItem(position) as HeroUI.Header)
        is ButtonViewHolder -> holder.bind(item = getItem(position) as HeroUI.Button)
        else -> throw Throwable("onBindViewHolder exception - unknown holder of view by name ${holder.itemView.id}")
    }

    override fun getItemViewType(position: Int): Int = when(getItem(position)) {
        is HeroUI.Hero -> R.layout.item_hero
        is HeroUI.Header -> R.layout.item_header
        is HeroUI.Button -> R.layout.item_button
        else -> throw Exception("getItemViewType unknown item class exception from position: $position")
    }
}

class HeroViewHolder(
    private val binding: ItemHeroBinding,
    private val onClickItem: (hero: HeroUI.Hero) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: HeroUI.Hero){
        binding.nameHero.text = item.name
        Glide.with(binding.photoHero)
            .load(item.image)
            .into(binding.photoHero)
        setListener(item)
    }
    private fun setListener(item: HeroUI.Hero) {
        binding.root.setOnClickListener{
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

class HeaderViewHolder(private val binding: ItemHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: HeroUI.Header) {
        binding.headerTitle.text = item.title
    }
    companion object {
        fun from(parent: ViewGroup): RecyclerView.ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemHeaderBinding.inflate(layoutInflater, parent, false)
            return HeaderViewHolder(binding)
        }
    }
}

class ButtonViewHolder(
    private val binding: ItemButtonBinding,
    private val onClickButton: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: HeroUI.Button){
        setListener()
    }
    private fun setListener() {
        binding.nextBtn.setOnClickListener {
            onClickButton
        }
    }
    companion object {
        fun from(parent: ViewGroup, onClickButton: () -> Unit): RecyclerView.ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemButtonBinding.inflate(layoutInflater, parent, false)
            return ButtonViewHolder(binding, onClickButton)
        }
    }
}

class HeroUIDiffUtil: DiffUtil.ItemCallback<HeroUI>() {
    override fun areItemsTheSame(oldItem: HeroUI, newItem: HeroUI): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: HeroUI, newItem: HeroUI): Boolean = oldItem == newItem
}