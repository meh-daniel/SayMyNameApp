package meh.daniel.com.hero_list_feature

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import meh.daniel.com.core.BaseFragment
import meh.daniel.com.hero_list_feature.databinding.FragmentHerolistBinding

class HeroListFragment() : BaseFragment<HeroListViewModel, FragmentHerolistBinding>(R.layout.fragment_herolist){

    override val viewModel: HeroListViewModel by viewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHerolistBinding = FragmentHerolistBinding.inflate(inflater, container, false)



}