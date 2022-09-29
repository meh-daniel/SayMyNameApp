package meh.daniel.com.feature_main.charactersearch

sealed class CharacterUI {
    data class Character(
        val id: Int,
        val name: String,
        val image: String,
        val birthdate: String,
    ): CharacterUI()
}