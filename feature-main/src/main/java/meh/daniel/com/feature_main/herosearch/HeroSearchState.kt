package meh.daniel.com.feature_main.herosearch

import meh.daniel.com.feature_main.herolist.HeroUI

interface HeroSearchState {
    object Idle : HeroSearchState
    object Loading : HeroSearchState
    data class Loaded(val heroes: List<HeroUI>) : HeroSearchState
    data class Error(val error: String) : HeroSearchState
    object Empty : HeroSearchState
}