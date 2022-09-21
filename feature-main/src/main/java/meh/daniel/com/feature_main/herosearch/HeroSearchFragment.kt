package meh.daniel.com.feature_main.herosearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import meh.daniel.com.core.BaseFragment
import meh.daniel.com.feature_main.R
import meh.daniel.com.feature_main.databinding.FragmentHerosearchBinding

@AndroidEntryPoint
class HeroSearchFragment : BaseFragment<HeroSearchViewModel, FragmentHerosearchBinding>(R.layout.fragment_herosearch) {

    override val viewModel: HeroSearchViewModel by viewModels()

    private val heroAdapter = HeroAdapter {
        routeToDetails(it.id)
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHerosearchBinding = FragmentHerosearchBinding.inflate(inflater, container, false)

    override fun setupSubscribers() {
        setupSubscriberState()
        setupSubscriberAction()
    }

    override fun initialize() {
        initHeroRecycler()
    }

    override fun setupListeners() {
        initListenerSearchButton()
    }

    private fun setupSubscriberAction() {
        viewModel.actionFlow.onEach { action ->
            when(action) {
                is HeroSearchAction.SearchByName -> {
                    viewModel.loadHeroList(action.name)
                }
                is HeroSearchAction.ShowError -> {

                }
            }
        }.launchIn(viewModel.viewModelScope)
    }

    private fun setupSubscriberState(){
        viewModel.stateFlow.onEach { state ->
            with(binding){
                heroListByNameMessage.text = when(state) {
                    is HeroSearchState.Idle -> getString(meh.daniel.com.core_ui.R.string.start)
                    is HeroSearchState.Empty -> getString(meh.daniel.com.core_ui.R.string.empty)
                    is HeroSearchState.Error -> state.error
                    else -> ""
                }
                heroListByNameMessage.visibility = when(state) {
                    is HeroSearchState.Idle -> View.VISIBLE
                    is HeroSearchState.Empty -> View.VISIBLE
                    is HeroSearchState.Error -> View.VISIBLE
                    else -> View.GONE
                }
                heroListByNameProgBar.visibility = if(state is HeroSearchState.Loading) View.VISIBLE else View.GONE
                heroListByNameRv.visibility = if(state is HeroSearchState.Loaded) View.VISIBLE else View.GONE
                if(state is HeroSearchState.Loaded) heroAdapter.submitList(state.heroes) else heroAdapter.submitList(emptyList())
            }
        }.launchIn(viewModel.viewModelScope)
    }

    private fun initListenerSearchButton(){
        binding.buttonSearch.setOnClickListener {
            viewModel.onSearchButtonPressed(
                binding.searchNameEdTxt.text.toString()
            )
        }
    }

    private fun initHeroRecycler() {
        with(binding) {
            heroListByNameRv.adapter = heroAdapter
            heroListByNameRv.layoutManager =
                LinearLayoutManager(
                    this@HeroSearchFragment.context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }
    }
    private fun routeToDetails(id: Int){
        val bundle = Bundle()
        bundle.putString("IdHero", id.toString())
        findNavController().navigate(R.id.action_heroSearchFragment_to_heroDetailInfoFragment, bundle)
    }
}