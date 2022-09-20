package meh.daniel.com.hero_list_feature

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import meh.daniel.com.core.BaseViewModel
import meh.daniel.com.hero_component.domain.HeroBreakingBadRepository

@HiltViewModel
class HeroListViewModel @Inject constructor(
    private val repository: HeroBreakingBadRepository
) : BaseViewModel() {

    private val _heroListState = MutableStateFlow<HeroListState>(HeroListState.Loading)
    val heroListState: Flow<HeroListState> = _heroListState.asStateFlow()

//    private val _listHeroEpisode = MutableStateFlow<List<HeroUI>>(emptyList())
//    val listHeroEpisode: StateFlow<List<HeroUI>> = _listHeroEpisode.asStateFlow()

    private val _numberCurrentEpisode = MutableStateFlow(1)
    val numberCurrentEpisode: StateFlow<Int> = _numberCurrentEpisode.asStateFlow()

    init {
        initHeroListState()
    }

    private fun initHeroListState() {
        viewModelScope.launch(Dispatchers.IO) {
            val heroUiData: MutableList<HeroUI> = mutableListOf()
            if (_heroListState.value is HeroListState.Loading) {
                try {
                    val heroList = repository.getEpisode(numberCurrentEpisode.value).hero
                    if (heroList.isEmpty()) {
                        _heroListState.value = HeroListState.Empty
                    } else {
                        heroUiData.add(HeroUI.Header(repository.getEpisode(numberCurrentEpisode.value).name))
                        heroUiData.addAll(heroList.toUI())
                        heroUiData.add(HeroUI.Button)
                        _heroListState.value = HeroListState.Loaded(heroUiData)
                    }
                } catch (e: Throwable) {
                    _heroListState.value = when (e) {
                        is NetworkErrorException -> HeroListState.Error(e.message.toString())
                        else -> HeroListState.Error(e.message.toString())
                    }
                }
            }
        }
    }

    fun routeToDetails(hero: HeroUI.Hero) {
        Log.d("xxx", "12312312")
    }

    fun routeToNextEpisode() {
        _numberCurrentEpisode.value++
        _heroListState.value = HeroListState.Loading
        initHeroListState()
    }

}