package meh.daniel.com.feature_main.herolist

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
import meh.daniel.com.feature_main.databinding.FragmentHerolistBinding

@AndroidEntryPoint
class HeroListFragment : BaseFragment<HeroListViewModel, FragmentHerolistBinding>(R.layout.fragment_herolist){

    override val viewModel: HeroListViewModel by viewModels()

    private val heroAdapter = HeroAdapter({
        routeToDetails(it.id)
    }, {
        viewModel.routeToNextEpisode()
    })

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHerolistBinding = FragmentHerolistBinding.inflate(inflater, container, false)

    override fun initialize() {
        initHeroRecycler()
    }

    override fun setupSubscribers() {
        setupHeroListState()
    }

    private fun setupHeroListState() {
        viewModel.heroListState.onEach { state ->
            with(binding) {
                heroListRc.visibility = if(state is HeroListState.Loaded) View.VISIBLE else View.GONE
                if(state is HeroListState.Loaded) heroAdapter.submitList(state.repos)
                progressBar.visibility = if (state is HeroListState.Loading) View.VISIBLE else View.GONE
                messageTxt.visibility = when(state) {
                    is HeroListState.Empty -> View.VISIBLE
                    is HeroListState.Error -> View.VISIBLE
                    else -> View.GONE
                }
                messageTxt.text = when(state) {
                    is HeroListState.Empty -> getString(meh.daniel.com.core_ui.R.string.empty)
                    is HeroListState.Error -> state.error
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
                    this@HeroListFragment.context,
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