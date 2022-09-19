package meh.daniel.com.hero_list_feature

sealed class HeroUI {
    data class Hero(
        val name: String,
        val image: String
    ): HeroUI()
    data class Header(
        val title: String
    ): HeroUI()
    object Button : HeroUI()
}