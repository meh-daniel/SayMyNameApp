package meh.daniel.com.saymynameapp.presentation.screens.characterdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import meh.daniel.com.saymynameapp.domain.SerialRepository

@HiltViewModel
class CharacterDetailInfoViewModel @Inject constructor(
    private val repository: SerialRepository
) : ViewModel() {

    private val _characterState = MutableStateFlow<CharacterDetailInfoState>(CharacterDetailInfoState.Loading)
    val characterState: Flow<CharacterDetailInfoState> = _characterState.asStateFlow()

    fun loadData(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            if (_characterState.value is CharacterDetailInfoState.Loading) {
                try {
                    val heroData = repository.getCharacterDetailsBy(id)
                    if (heroData.name.isBlank()) {
                        _characterState.value = CharacterDetailInfoState.Empty
                    } else {
                        _characterState.value = CharacterDetailInfoState.Loaded(heroData)
                    }
                } catch (e: Throwable) {
                    _characterState.value = CharacterDetailInfoState.Error(e.message.toString())
                }
            }
        }
    }
}