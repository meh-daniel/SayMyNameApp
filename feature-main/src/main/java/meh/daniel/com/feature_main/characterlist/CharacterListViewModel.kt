package meh.daniel.com.feature_main.characterlist

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
class CharacterListViewModel @Inject constructor(
    private val repository: SerialRepository
) : BaseViewModel() {

    private val _characterListState = MutableStateFlow<CharacterListState>(CharacterListState.Loading)
    val characterListState: Flow<CharacterListState> = _characterListState.asStateFlow()

    private val _numberCurrentEpisode = MutableStateFlow(1)
    val numberCurrentEpisode: StateFlow<Int> = _numberCurrentEpisode.asStateFlow()

    init {
        loadHeroList()
    }

    private fun loadHeroList() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_characterListState.value is CharacterListState.Loading) {
                try {
                    val heroData = repository.getEpisode(numberCurrentEpisode.value)
                    if (heroData.name.isEmpty()) {
                        _characterListState.value = CharacterListState.Empty
                    } else {
                        val characterUiData : MutableList<CharacterUI> = mutableListOf()
                        characterUiData.add(CharacterUI.Header(heroData.name))
                        characterUiData.addAll(heroData.characters.toUI())
                        characterUiData.add(CharacterUI.Button)
                        _characterListState.value = CharacterListState.Loaded(characterUiData)
                    }
                } catch (e: Throwable) {
                    _characterListState.value = when (e) {
                        is NetworkErrorException -> CharacterListState.Error(e.message.toString())
                        else -> CharacterListState.Error(e.message.toString())
                    }
                }
            }
        }
    }

    fun routeToNextEpisode() {
        _numberCurrentEpisode.value++
        _characterListState.value = CharacterListState.Loading
        loadHeroList()
    }

}