package meh.daniel.com.hero_list_feature.herodetail

import android.accounts.NetworkErrorException
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import meh.daniel.com.core.BaseViewModel
import meh.daniel.com.hero_component.domain.HeroBreakingBadRepository

@HiltViewModel
class HeroDetailInfoViewModel @Inject constructor(
    private val repository: HeroBreakingBadRepository
) : BaseViewModel() {

    private val _heroState = MutableStateFlow<HeroDetailInfoState>(HeroDetailInfoState.Loading)
    val heroState: Flow<HeroDetailInfoState> = _heroState.asStateFlow()

    fun loadData(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            if (_heroState.value is HeroDetailInfoState.Loading) {
                try {
                    val heroData = repository.getById(id)
                    if (heroData.name.isEmpty()) {
                        _heroState.value = HeroDetailInfoState.Empty
                    } else {
                        _heroState.value = HeroDetailInfoState.Loaded(heroData)
                    }
                } catch (e: Throwable) {
                    _heroState.value = when (e) {
                        is NetworkErrorException -> HeroDetailInfoState.Error(e.message.toString())
                        else -> HeroDetailInfoState.Error(e.message.toString())
                    }
                }
            }
        }
    }


}