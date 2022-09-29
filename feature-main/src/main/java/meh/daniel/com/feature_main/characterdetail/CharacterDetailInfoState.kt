package meh.daniel.com.feature_main.characterdetail

import meh.daniel.com.serial_component.model.CharacterDetails

sealed interface CharacterDetailInfoState{
    object Loading : CharacterDetailInfoState
    data class Loaded(val hero: CharacterDetails) : CharacterDetailInfoState
    data class Error(val error: String) : CharacterDetailInfoState
    object Empty : CharacterDetailInfoState
}