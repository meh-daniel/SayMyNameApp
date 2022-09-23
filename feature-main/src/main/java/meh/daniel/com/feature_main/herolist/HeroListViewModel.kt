package meh.daniel.com.feature_main.herolist

import android.accounts.NetworkErrorException
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
import meh.daniel.com.serial_component.SerialRepository

@HiltViewModel
class HeroListViewModel @Inject constructor(
    private val repository: SerialRepository
) : BaseViewModel() {

    private val _heroListState = MutableStateFlow<HeroListState>(HeroListState.Loading)
    val heroListState: Flow<HeroListState> = _heroListState.asStateFlow()

    private val _numberCurrentEpisode = MutableStateFlow(1)
    val numberCurrentEpisode: StateFlow<Int> = _numberCurrentEpisode.asStateFlow()

    init {
        loadHeroList()
    }

    private fun loadHeroList() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_heroListState.value is HeroListState.Loading) {
                try {
                    val heroData = repository.getEpisode(numberCurrentEpisode.value)
                    if (heroData.name.isEmpty()) {
                        _heroListState.value = HeroListState.Empty
                    } else {
                        val heroUiData : MutableList<HeroUI> = mutableListOf()
                        heroUiData.add(HeroUI.Header(heroData.name))
                        heroUiData.addAll(heroData.characters.toUI())
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

    fun routeToNextEpisode() {
        _numberCurrentEpisode.value++
        _heroListState.value = HeroListState.Loading
        loadHeroList()
    }

}