package meh.daniel.com.feature_main.charactersearch

interface CharacterSearchState {
    object Idle : CharacterSearchState
    object Loading : CharacterSearchState
    data class Loaded(val character: List<CharacterUI>) : CharacterSearchState
    data class Error(val error: String) : CharacterSearchState
    object Empty : CharacterSearchState
}