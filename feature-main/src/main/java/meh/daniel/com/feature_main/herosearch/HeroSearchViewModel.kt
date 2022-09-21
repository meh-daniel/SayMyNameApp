package meh.daniel.com.feature_main.herosearch

import android.accounts.NetworkErrorException
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import meh.daniel.com.core.BaseViewModel
import meh.daniel.com.hero_component.domain.HeroRepository

@HiltViewModel
class HeroSearchViewModel @Inject constructor(
    private val repository: HeroRepository
) : BaseViewModel(){
    private val _action: Channel<HeroSearchAction> = Channel(Channel.BUFFERED)
    var actionFlow: Flow<HeroSearchAction> = _action.receiveAsFlow()

    private val _state = MutableStateFlow<HeroSearchState>(HeroSearchState.Idle)
    val stateFlow: Flow<HeroSearchState> = _state.asStateFlow()

    private fun loadHeroList() {
        viewModelScope.launch(Dispatchers.IO) {
//            if (_state.value is HeroSearchState.Loading) {
//                try {
//                    val heroData = repository.getEpisode(numberCurrentEpisode.value)
//                    if (heroData.name.isEmpty()) {
//                        _state.value = HeroListState.Empty
//                    } else {
//                        val heroUiData : MutableList<HeroUI> = mutableListOf()
//                        heroUiData.add(HeroUI.Header(heroData.name))
//                        heroUiData.addAll(heroData.hero.toUI())
//                        heroUiData.add(HeroUI.Button)
//                        _heroListState.value = HeroListState.Loaded(heroUiData)
//                    }
//                } catch (e: Throwable) {
//                    _heroListState.value = when (e) {
//                        is NetworkErrorException -> HeroListState.Error(e.message.toString())
//                        else -> HeroListState.Error(e.message.toString())
//                    }
//                }
//            }
        }
    }
}