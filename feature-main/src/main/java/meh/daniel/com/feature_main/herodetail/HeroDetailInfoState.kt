package meh.daniel.com.feature_main.herodetail

sealed interface HeroDetailInfoState{
    object Loading : HeroDetailInfoState
    data class Loaded(val hero: meh.daniel.com.serial_component.model.CharacterDetails) : HeroDetailInfoState
    data class Error(val error: String) : HeroDetailInfoState
    object Empty : HeroDetailInfoState
}