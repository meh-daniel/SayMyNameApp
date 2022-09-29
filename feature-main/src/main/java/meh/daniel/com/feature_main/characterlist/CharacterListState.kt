package meh.daniel.com.feature_main.characterlist

sealed interface CharacterListState {
    object Loading : CharacterListState
    data class Loaded(val repos: List<CharacterUI>) : CharacterListState
    data class Error(val error: String) : CharacterListState
    object Empty : CharacterListState
}