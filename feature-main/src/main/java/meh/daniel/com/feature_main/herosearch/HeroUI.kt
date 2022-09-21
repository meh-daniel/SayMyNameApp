package meh.daniel.com.feature_main.herosearch

sealed class HeroUI {
    data class Hero(
        val id: Int,
        val name: String,
        val image: String,
        val birthdate: String,
    ): HeroUI()
}