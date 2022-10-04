package meh.daniel.com.saymynameapp.presentation.screens.characterlist

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
import meh.daniel.com.saymynameapp.databinding.FragmentCharacterlistBinding
import meh.daniel.com.saymynameapp.core.BaseFragment

const val ID_CHARACTER = "id_character"

@AndroidEntryPoint
class CharacterListFragment : BaseFragment<CharacterListViewModel, FragmentCharacterlistBinding>(R.layout.fragment_characterlist){

    override val viewModel: CharacterListViewModel by viewModels()

    private val characterAdapter = CharacterAdapter(
        onClickCharacter = { onClickCharacter -> routeToDetails(onClickCharacter.id) },
        onClickButton = { viewModel.routeToNextEpisode() }
    )

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCharacterlistBinding = FragmentCharacterlistBinding.inflate(inflater, container, false)

    override fun initialize() {
        initHeroRecycler()
    }

    override fun setupSubscribers() {
        setupHeroListState()
    }

    private fun setupHeroListState() {
        viewModel.characterListState.onEach { state ->
            with(binding) {
                heroListRc.visibility = if(state is CharacterListState.Loaded) View.VISIBLE else View.GONE
                if(state is CharacterListState.Loaded) characterAdapter.submitList(state.repos)
                progressBar.visibility = if (state is CharacterListState.Loading) View.VISIBLE else View.GONE
                messageTxt.visibility = when(state) {
                    is CharacterListState.Empty -> View.VISIBLE
                    is CharacterListState.Error -> View.VISIBLE
                    else -> View.GONE
                }
                messageTxt.text = when(state) {
                    is CharacterListState.Empty -> getString(R.string.empty)
                    is CharacterListState.Error -> state.error
                    else -> ""
                }

            }
        }.launchIn(viewModel.viewModelScope)
    }

    private fun initHeroRecycler() {
        with(binding) {
            heroListRc.adapter = characterAdapter
            heroListRc.layoutManager =
                LinearLayoutManager(
                    this@CharacterListFragment.context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }
    }

    private fun routeToDetails(id: Int){
        val bundle = Bundle()
        bundle.putString(ID_CHARACTER, id.toString())
        findNavController().navigate(R.id.action_heroListFragment_to_heroDetailInfoFragment, bundle)
    }

}