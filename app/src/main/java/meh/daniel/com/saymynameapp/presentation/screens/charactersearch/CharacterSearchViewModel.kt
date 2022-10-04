package meh.daniel.com.saymynameapp.presentation.screens.charactersearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import meh.daniel.com.saymynameapp.core.BaseViewModel
import meh.daniel.com.saymynameapp.domain.SerialRepository
import meh.daniel.com.saymynameapp.presentation.toUI

@HiltViewModel
class CharacterSearchViewModel @Inject constructor(
    private val repository: SerialRepository
) : BaseViewModel() {

    private val _action = MutableLiveData<CharacterSearchAction>()
    val action: LiveData<CharacterSearchAction> get()= _action

    private val _state = MutableLiveData<CharacterSearchState>(CharacterSearchState.Idle)
    val state: LiveData<CharacterSearchState> get()= _state

    fun loadCharacterList(nameInput: String) {
        _state.value = CharacterSearchState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            if (_state.value is CharacterSearchState.Loading) {
                try {
                    val heroData = repository.getCharacterBy(nameInput)
                    if (heroData.isEmpty()) {
                        setState(CharacterSearchState.Empty)
                    } else {
                        setState(CharacterSearchState.Loaded(heroData.toUI()))
                    }
                } catch (e: Throwable) {
                    setState(CharacterSearchState.Error(e.message.toString()))
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

    private fun setState(state: CharacterSearchState) {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = state
        }
    }

    private fun sendAction(action: CharacterSearchAction){
        viewModelScope.launch(Dispatchers.Main){
            _action.value = action
        }
    }

}