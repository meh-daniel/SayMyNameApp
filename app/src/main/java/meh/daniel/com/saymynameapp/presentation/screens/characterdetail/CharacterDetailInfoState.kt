package meh.daniel.com.saymynameapp.presentation.screens.characterdetail

import meh.daniel.com.saymynameapp.domain.model.CharacterDetails

sealed interface CharacterDetailInfoState{
    object Loading : CharacterDetailInfoState
    data class Loaded(val hero: CharacterDetails) : CharacterDetailInfoState
    data class Error(val error: String) : CharacterDetailInfoState
    object Empty : CharacterDetailInfoState
}