package meh.daniel.com.feature_main.herosearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import meh.daniel.com.core.BaseFragment
import meh.daniel.com.feature_main.R
import meh.daniel.com.feature_main.databinding.FragmentHerosearchBinding

@AndroidEntryPoint
class HeroSearchFragment : BaseFragment<HeroSearchViewModel, FragmentHerosearchBinding>(R.layout.fragment_herosearch) {
    override val viewModel: HeroSearchViewModel by viewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHerosearchBinding = FragmentHerosearchBinding.inflate(inflater, container, false)

    override fun setupSubscribers() {
        setupSubscriberState()
        setupSubscriberAction()
    }

    private fun setupSubscriberAction() {
        viewModel.actionFlow.onEach { action ->
            when(action) {
                is HeroSearchAction.SearchByName -> {

                }
                is HeroSearchAction.ShowError -> {

                }
            }
        }.launchIn(viewModel.viewModelScope)
    }

    private fun setupSubscriberState(){
        viewModel.stateFlow.onEach { state ->
            with(binding){

            }
        }.launchIn(viewModel.viewModelScope)
    }

}