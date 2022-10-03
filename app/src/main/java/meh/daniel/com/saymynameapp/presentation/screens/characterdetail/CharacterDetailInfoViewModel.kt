package meh.daniel.com.saymynameapp.presentation.screens.characterdetail

import android.accounts.NetworkErrorException
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import meh.daniel.com.saymynameapp.domain.SerialRepository
import meh.daniel.com.saymynameapp.core.BaseViewModel

@HiltViewModel
class CharacterDetailInfoViewModel @Inject constructor(
    private val repository: SerialRepository
) : BaseViewModel() {

    private val _heroState = MutableStateFlow<CharacterDetailInfoState>(CharacterDetailInfoState.Loading)
    val heroState: Flow<CharacterDetailInfoState> = _heroState.asStateFlow()

    fun loadData(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            if (_heroState.value is CharacterDetailInfoState.Loading) {
                try {
                    val heroData = repository.getCharacterDetailsBy(id)
                    if (heroData.name.isEmpty()) {
                        _heroState.value = CharacterDetailInfoState.Empty
                    } else {
                        _heroState.value = CharacterDetailInfoState.Loaded(heroData)
                    }
                } catch (e: Throwable) {
                    _heroState.value = when (e) {
                        is NetworkErrorException -> CharacterDetailInfoState.Error(e.message.toString())
                        else -> CharacterDetailInfoState.Error(e.message.toString())
                    }
                }
            }
        }
    }
}