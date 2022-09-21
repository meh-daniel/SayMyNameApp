package meh.daniel.com.feature_main.herosearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import meh.daniel.com.core.BaseFragment
import meh.daniel.com.feature_main.R
import meh.daniel.com.feature_main.databinding.FragmentHerosearchBinding

class HeroSearchFragment : BaseFragment<HeroSearchViewModel, FragmentHerosearchBinding>(R.layout.fragment_herosearch) {
    override val viewModel: HeroSearchViewModel by viewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHerosearchBinding = FragmentHerosearchBinding.inflate(inflater, container, false)
}