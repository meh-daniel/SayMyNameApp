package meh.daniel.com.hero_list_feature

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import meh.daniel.com.hero_list_feature.databinding.ItemButtonNextBinding
import meh.daniel.com.hero_list_feature.databinding.ItemHeaderBinding
import meh.daniel.com.hero_list_feature.databinding.ItemHeroBinding

class HeroAdapter(
    private val onClickHero: (hero: HeroUI.Hero) -> Unit,
    private val onClickButton: () -> Unit
) : ListAdapter<HeroUI, RecyclerView.ViewHolder>(HeroUIDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when(viewType) {
        R.layout.item_hero -> HeroViewHolder.from(parent, onClickHero)
        R.layout.item_header -> HeaderViewHolder.from(parent)
        R.layout.item_button_next -> ButtonNextViewHolder.from(parent, onClickButton)
        else -> throw Throwable("onCreateViewHolder exception - unknown view type by name: $viewType")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = when(holder) {
        is HeroViewHolder -> holder.bind(item = getItem(position) as HeroUI.Hero)
        is HeaderViewHolder -> holder.bind(item = getItem(position) as HeroUI.Header)
        is ButtonNextViewHolder -> holder.bind(item = getItem(position) as HeroUI.Button)
        else -> throw Throwable("onBindViewHolder exception - unknown holder of view by name ${holder.itemView.id}")
    }

    override fun getItemViewType(position: Int): Int = when(getItem(position)) {
        is HeroUI.Hero -> R.layout.item_hero
        is HeroUI.Header -> R.layout.item_header
        is HeroUI.Button -> R.layout.item_button_next
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

class HeaderViewHolder(private val binding: ItemHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: HeroUI.Header) {
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
    fun bind(item: HeroUI.Button){
        setListener(item)
    }
    private fun setListener(item: HeroUI.Button) {
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

class HeroUIDiffUtil: DiffUtil.ItemCallback<HeroUI>() {
    override fun areItemsTheSame(oldItem: HeroUI, newItem: HeroUI): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: HeroUI, newItem: HeroUI): Boolean = oldItem == newItem
}