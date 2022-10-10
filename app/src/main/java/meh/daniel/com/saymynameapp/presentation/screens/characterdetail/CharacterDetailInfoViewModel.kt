package meh.daniel.com.saymynameapp.presentation.screens.characterdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import meh.daniel.com.saymynameapp.core.BaseViewModel
import meh.daniel.com.saymynameapp.domain.SerialRepository

@HiltViewModel
class CharacterDetailInfoViewModel @Inject constructor(
    private val repository: SerialRepository
) : BaseViewModel() {

    private val _state = MutableLiveData<CharacterDetailInfoState>(CharacterDetailInfoState.Loading)
    val state: LiveData<CharacterDetailInfoState> get()= _state

    fun loadData(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            if (_state.value is CharacterDetailInfoState.Loading) {
                try {
                    val heroData = repository.getCharacterDetailsBy(id)
                    if (heroData.name.isBlank()) {
                        setState(CharacterDetailInfoState.Empty)
                    } else {
                        setState(CharacterDetailInfoState.Loaded(heroData))
                    }
                } catch (e: Throwable) {
                    setState(CharacterDetailInfoState.Error(e.message.toString()))
                }
            }
        }
    }

    private fun setState(state: CharacterDetailInfoState) {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = state
        }
    }
}