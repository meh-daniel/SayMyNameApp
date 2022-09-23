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
import meh.daniel.com.serial_component.SerialRepository

@HiltViewModel
class HeroSearchViewModel @Inject constructor(
    private val repository: SerialRepository
) : BaseViewModel(){

    private val _action: Channel<HeroSearchAction> = Channel(Channel.BUFFERED)
    var actionFlow: Flow<HeroSearchAction> = _action.receiveAsFlow()

    private val _state = MutableStateFlow<HeroSearchState>(HeroSearchState.Idle)
    val stateFlow: Flow<HeroSearchState> = _state.asStateFlow()

    fun loadHeroList(nameInput: String) {
        _state.value = HeroSearchState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            if (_state.value is HeroSearchState.Loading) {
                try {
                    val heroData = repository.getHeroBy(nameInput)
                    if (heroData.isEmpty()) {
                        _state.value = HeroSearchState.Empty
                    } else {
                        _state.value = HeroSearchState.Loaded(heroData.toUI())
                    }
                } catch (e: Throwable) {
                    _state.value = when (e) {
                        is NetworkErrorException -> HeroSearchState.Error(e.message.toString())
                        else -> HeroSearchState.Error(e.message.toString())
                    }
                }
            }
        }
    }

    fun onSearchButtonPressed(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if(name.isNotEmpty()){
                sendAction(HeroSearchAction.SearchByName(name))
            }
        }
    }

    private fun sendAction(action: HeroSearchAction){
        viewModelScope.launch(Dispatchers.Main){
            _action.send(action)
        }
    }

}