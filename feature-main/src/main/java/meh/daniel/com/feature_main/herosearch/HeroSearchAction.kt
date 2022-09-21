package meh.daniel.com.feature_main.herosearch

interface HeroSearchAction {
    data class ShowError(val message: String) : HeroSearchAction
    data class SearchByName(val name: String) : HeroSearchAction
}