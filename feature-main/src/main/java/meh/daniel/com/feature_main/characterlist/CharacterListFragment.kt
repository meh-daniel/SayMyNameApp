package meh.daniel.com.feature_main.characterlist

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
import meh.daniel.com.feature_main.databinding.FragmentCharacterlistBinding

@AndroidEntryPoint
class CharacterListFragment : BaseFragment<CharacterListViewModel, FragmentCharacterlistBinding>(R.layout.fragment_characterlist){

    override val viewModel: CharacterListViewModel by viewModels()

    private val heroAdapter = HeroAdapter({
        routeToDetails(it.id)
    }, {
        viewModel.routeToNextEpisode()
    })

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
                if(state is CharacterListState.Loaded) heroAdapter.submitList(state.repos)
                progressBar.visibility = if (state is CharacterListState.Loading) View.VISIBLE else View.GONE
                messageTxt.visibility = when(state) {
                    is CharacterListState.Empty -> View.VISIBLE
                    is CharacterListState.Error -> View.VISIBLE
                    else -> View.GONE
                }
                messageTxt.text = when(state) {
                    is CharacterListState.Empty -> getString(meh.daniel.com.core_ui.R.string.empty)
                    is CharacterListState.Error -> state.error
                    else -> ""
                }

            }
        }.launchIn(viewModel.viewModelScope)
    }

    private fun initHeroRecycler() {
        with(binding) {
            heroListRc.adapter = heroAdapter
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
        bundle.putString("IdHero", id.toString())
        findNavController().navigate(R.id.action_heroListFragment_to_heroDetailInfoFragment, bundle)
    }

}