package meh.daniel.com.hero_list_feature

sealed interface HeroListState {
    object Loading : HeroListState
    data class Loaded(val repos: List<HeroUI>) : HeroListState
    data class Error(val error: String) : HeroListState
    object Empty : HeroListState
}