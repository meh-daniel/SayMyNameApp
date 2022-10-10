package meh.daniel.com.saymynameapp.presentation.screens.charactersearch

import meh.daniel.com.saymynameapp.presentation.model.CharacterUI

interface CharacterSearchState {
    object Idle : CharacterSearchState
    object Loading : CharacterSearchState
    data class Loaded(val character: List<CharacterUI>) : CharacterSearchState
    data class Error(val error: String) : CharacterSearchState
    object Empty : CharacterSearchState
}