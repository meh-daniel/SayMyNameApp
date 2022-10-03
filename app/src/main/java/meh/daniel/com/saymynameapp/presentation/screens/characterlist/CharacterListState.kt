package meh.daniel.com.saymynameapp.presentation.screens.characterlist

import meh.daniel.com.saymynameapp.presentation.model.CharacterUI

sealed interface CharacterListState {
    object Loading : CharacterListState
    data class Loaded(val repos: List<CharacterUI>) : CharacterListState
    data class Error(val error: String) : CharacterListState
    object Empty : CharacterListState
}