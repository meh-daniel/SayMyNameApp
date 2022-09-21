package meh.daniel.com.feature_main.herodetail

import meh.daniel.com.hero_component.domain.model.HeroDetails

sealed interface HeroDetailInfoState{
    object Loading : HeroDetailInfoState
    data class Loaded(val hero: HeroDetails) : HeroDetailInfoState
    data class Error(val error: String) : HeroDetailInfoState
    object Empty : HeroDetailInfoState
}