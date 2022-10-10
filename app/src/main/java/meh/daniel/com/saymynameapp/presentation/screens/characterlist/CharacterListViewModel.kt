package meh.daniel.com.saymynameapp.presentation.screens.characterlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import meh.daniel.com.saymynameapp.core.BaseViewModel
import meh.daniel.com.saymynameapp.domain.SerialRepository
import meh.daniel.com.saymynameapp.presentation.model.CharacterUI
import meh.daniel.com.saymynameapp.presentation.toUI

private const val firstEpisode = 1

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val repository: SerialRepository
) : BaseViewModel() {

    private val _characterListState = MutableLiveData<CharacterListState>(CharacterListState.Loading)
    val characterListState: LiveData<CharacterListState> get() = _characterListState


    private var _charterNumber : Int = firstEpisode

    init {
        loadHeroList()
    }

    private fun loadHeroList() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_characterListState.value is CharacterListState.Loading) {
                try {
                    val heroData = repository.getEpisode(_charterNumber)
                    if (heroData.name.isBlank()) {
                        setState(CharacterListState.Empty)
                    } else {
                        val characterUiData : MutableList<CharacterUI> = mutableListOf()
                        characterUiData.add(CharacterUI.Header(heroData.name))
                        characterUiData.addAll(heroData.characters.toUI())
                        characterUiData.add(CharacterUI.Button)
                        setState(CharacterListState.Loaded(characterUiData))
                    }
                } catch (e: Throwable) {
                    setState(CharacterListState.Error(e.message.toString()))
                }
            }
        }
    }

    private fun setState(state: CharacterListState) {
        viewModelScope.launch(Dispatchers.Main) {
            _characterListState.value = state
        }
    }

    fun routeToNextEpisode() {
        viewModelScope.launch(Dispatchers.Main){
            _charterNumber++
            setState(CharacterListState.Loading)
            loadHeroList()
        }
    }

}