package meh.daniel.com.saymynameapp.presentation.screens.charactersearch

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
import meh.daniel.com.saymynameapp.R
import meh.daniel.com.saymynameapp.databinding.FragmentCharactersearchBinding
import meh.daniel.com.saymynameapp.core.BaseFragment

@AndroidEntryPoint
class CharacterSearchFragment : BaseFragment<CharacterSearchViewModel, FragmentCharactersearchBinding>(R.layout.fragment_charactersearch) {

    override val viewModel: CharacterSearchViewModel by viewModels()

    private val heroAdapter = HeroAdapter {
        routeToDetails(it.id)
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCharactersearchBinding = FragmentCharactersearchBinding.inflate(inflater, container, false)

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
                is CharacterSearchAction.SearchByName -> {
                    viewModel.loadHeroList(action.name)
                }
            }
        }.launchIn(viewModel.viewModelScope)
    }
    private fun setupSubscriberState(){
        viewModel.stateFlow.onEach { state ->
            with(binding){
                heroListByNameMessage.text = when(state) {
                    is CharacterSearchState.Idle -> getString(R.string.start)
                    is CharacterSearchState.Empty -> getString(R.string.empty)
                    is CharacterSearchState.Error -> state.error
                    else -> ""
                }
                heroListByNameMessage.visibility = when(state) {
                    is CharacterSearchState.Idle -> View.VISIBLE
                    is CharacterSearchState.Empty -> View.VISIBLE
                    is CharacterSearchState.Error -> View.VISIBLE
                    else -> View.GONE
                }
                heroListByNameProgBar.visibility = if(state is CharacterSearchState.Loading) View.VISIBLE else View.GONE
                heroListByNameRv.visibility = if(state is CharacterSearchState.Loaded) View.VISIBLE else View.GONE
                if(state is CharacterSearchState.Loaded) heroAdapter.submitList(state.character) else heroAdapter.submitList(emptyList())
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
                    this@CharacterSearchFragment.context,
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