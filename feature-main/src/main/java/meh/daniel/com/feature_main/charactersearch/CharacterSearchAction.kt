package meh.daniel.com.feature_main.charactersearch

interface CharacterSearchAction {
    data class SearchByName(val name: String) : CharacterSearchAction
}