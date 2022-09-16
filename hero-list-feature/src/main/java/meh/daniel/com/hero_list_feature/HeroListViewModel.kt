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

@HiltViewModel
class HeroListViewModel @Inject constructor() : BaseViewModel(){

    private val _heroListState = MutableStateFlow<HeroListState>(HeroListState.Loading)
    val heroListState: Flow<HeroListState> = _heroListState.asStateFlow()

    fun loadHeroList() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_heroListState.value is HeroListState.Loading){
                try {
                    val heroList = mutableListOf<String>("Вася", "Петя", "Чёрт")
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