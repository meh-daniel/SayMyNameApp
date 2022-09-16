package meh.daniel.com.hero_list_feature

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
import meh.daniel.com.hero_component.domain.HeroRepository

@HiltViewModel
class HeroListViewModel @Inject constructor(
    private val repository: HeroRepository
) : BaseViewModel(){

    private val _heroListState = MutableStateFlow<HeroListState>(HeroListState.Loading)
    val heroListState: Flow<HeroListState> = _heroListState.asStateFlow()

    fun loadHeroList() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_heroListState.value is HeroListState.Loading){
                try {
                    val heroList = repository.getAll()
                    if (heroList.isEmpty()){
                        _heroListState.value = HeroListState.Empty
                    } else {
                        _heroListState.value = HeroListState.Loaded(heroList)
                    }
                } catch (e : Throwable) {
                    _heroListState.value = when(e) {
                        is NetworkErrorException -> HeroListState.Error(e.message.toString())
                        else -> HeroListState.Error(e.message.toString())
                    }
                }
            }
        }
    }

    fun routeToDetails(id: Int){

    }

}