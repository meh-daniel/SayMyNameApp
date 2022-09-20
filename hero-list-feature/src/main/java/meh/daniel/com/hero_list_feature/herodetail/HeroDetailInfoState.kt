package meh.daniel.com.hero_list_feature.herodetail

import meh.daniel.com.hero_component.domain.Hero

sealed interface HeroDetailInfoState{
    object Loading : HeroDetailInfoState
    data class Loaded(val hero: Hero) : HeroDetailInfoState
    data class Error(val error: String) : HeroDetailInfoState
    object Empty : HeroDetailInfoState
}