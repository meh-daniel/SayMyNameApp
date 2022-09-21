package meh.daniel.com.feature_main.herolist

sealed class HeroUI {
    data class Hero(
        val id: Int,
        val name: String,
        val image: String,
        val birthdate: String,
    ): HeroUI()
    data class Header(
        val title: String,
    ): HeroUI()
    object Button : HeroUI()
}