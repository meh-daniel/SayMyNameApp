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

const val ID_CHARACTER = "id_character"

@AndroidEntryPoint
class CharacterSearchFragment : BaseFragment<CharacterSearchViewModel, FragmentCharactersearchBinding>(R.layout.fragment_charactersearch) {

    override val viewModel: CharacterSearchViewModel by viewModels()

    private val characterSearchAdapter = CharacterSearchAdapter (
        onClickCharacter = { onClickCharacter -> routeToDetails(onClickCharacter.id) }
    )

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCharactersearchBinding = FragmentCharactersearchBinding.inflate(inflater, container, false)

    override fun setupSubscribers() {
        setupSubscriberState()
        setupSubscriberAction()
    }

    override fun initialize() {
        initCharacterRecycler()
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
                characterListByNameMessage.text = when(state) {
                    is CharacterSearchState.Idle -> getString(R.string.start)
                    is CharacterSearchState.Empty -> getString(R.string.empty)
                    is CharacterSearchState.Error -> state.error
                    else -> ""
                }
                characterListByNameMessage.visibility = when(state) {
                    is CharacterSearchState.Idle -> View.VISIBLE
                    is CharacterSearchState.Empty -> View.VISIBLE
                    is CharacterSearchState.Error -> View.VISIBLE
                    else -> View.GONE
                }
                characterListByNameProgBar.visibility = if(state is CharacterSearchState.Loading) View.VISIBLE else View.GONE
                characterListByNameRv.visibility = if(state is CharacterSearchState.Loaded) View.VISIBLE else View.GONE
                if(state is CharacterSearchState.Loaded) characterSearchAdapter.submitList(state.character) else characterSearchAdapter.submitList(emptyList())
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

    private fun initCharacterRecycler() {
        with(binding) {
            characterListByNameRv.adapter = characterSearchAdapter
            characterListByNameRv.layoutManager =
                LinearLayoutManager(
                    this@CharacterSearchFragment.context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }
    }
    private fun routeToDetails(id: Int){
        val bundle = Bundle()
        bundle.putString(ID_CHARACTER, id.toString())
        findNavController().navigate(R.id.action_heroSearchFragment_to_heroDetailInfoFragment, bundle)
    }
}