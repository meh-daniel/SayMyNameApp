package meh.daniel.com.hero_list_feature

import meh.daniel.com.hero_component.domain.Hero

sealed interface HeroListState {
    object Loading : HeroListState
    data class Loaded(val repos: List<Hero>) : HeroListState
    data class Error(val error: String) : HeroListState
    object Empty : HeroListState
}