package meh.daniel.com.hero_detail_info_feature

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import meh.daniel.com.core.BaseFragment
import meh.daniel.com.hero_detail_info_feature.databinding.FragmentHerodetailinfoBinding

@AndroidEntryPoint
class HeroDetailInfoFragment : BaseFragment<HeroDetailInfoViewModel, FragmentHerodetailinfoBinding>(R.layout.fragment_herodetailinfo){
    override val viewModel: HeroDetailInfoViewModel by viewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHerodetailinfoBinding = FragmentHerodetailinfoBinding.inflate(inflater, container, false)

}