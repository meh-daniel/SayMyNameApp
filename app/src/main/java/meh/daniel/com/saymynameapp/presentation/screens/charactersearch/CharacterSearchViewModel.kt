package meh.daniel.com.saymynameapp.presentation.screens.charactersearch

import androidx.lifecycle.ViewModel
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
import meh.daniel.com.saymynameapp.domain.SerialRepository
import meh.daniel.com.saymynameapp.presentation.toUI

@HiltViewModel
class CharacterSearchViewModel @Inject constructor(
    private val repository: SerialRepository
) : ViewModel() {

    private val _action: Channel<CharacterSearchAction> = Channel(Channel.BUFFERED)
    var actionFlow: Flow<CharacterSearchAction> = _action.receiveAsFlow()

    private val _state = MutableStateFlow<CharacterSearchState>(CharacterSearchState.Idle)
    val stateFlow: Flow<CharacterSearchState> = _state.asStateFlow()

    fun loadHeroList(nameInput: String) {
        _state.value = CharacterSearchState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            if (_state.value is CharacterSearchState.Loading) {
                try {
                    val heroData = repository.getCharacterBy(nameInput)
                    if (heroData.isEmpty()) {
                        _state.value = CharacterSearchState.Empty
                    } else {
                        _state.value = CharacterSearchState.Loaded(heroData.toUI())
                    }
                } catch (e: Throwable) {
                    _state.value = CharacterSearchState.Error(e.message.toString())
                }
            }
        }
    }

    fun onSearchButtonPressed(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if(name.isNotEmpty()){
                sendAction(CharacterSearchAction.SearchByName(name))
            }
        }
    }

    private fun sendAction(action: CharacterSearchAction){
        viewModelScope.launch(Dispatchers.Main){
            _action.send(action)
        }
    }

}