package meh.daniel.com.saymynameapp.presentation.screens.charactersearch

interface CharacterSearchAction {
    data class SearchByName(val name: String) : CharacterSearchAction
}