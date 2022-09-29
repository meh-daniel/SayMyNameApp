package meh.daniel.com.saymynameapp.presentation.screen.charactersearch

interface CharacterSearchAction {
    data class SearchByName(val name: String) : CharacterSearchAction
}