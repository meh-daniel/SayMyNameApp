package meh.daniel.com.feature_main.herolist

sealed interface HeroListState {
    object Loading : HeroListState
    data class Loaded(val repos: List<HeroUI>) : HeroListState
    data class Error(val error: String) : HeroListState
    object Empty : HeroListState
}