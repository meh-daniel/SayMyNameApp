package meh.daniel.com.feature_main.herosearch

interface HeroSearchAction {
    data class ShowError(val message: String) : HeroSearchAction
    object SearchByName : HeroSearchAction
}