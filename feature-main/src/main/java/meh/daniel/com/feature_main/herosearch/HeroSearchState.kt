package meh.daniel.com.feature_main.herosearch

interface HeroSearchState {
    object Idle : HeroSearchState
    object Loading : HeroSearchState
    data class Loaded(val heroes: List<HeroUI>) : HeroSearchState
    data class Error(val error: String) : HeroSearchState
    object Empty : HeroSearchState
}