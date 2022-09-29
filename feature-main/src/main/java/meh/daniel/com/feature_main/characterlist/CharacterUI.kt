package meh.daniel.com.feature_main.characterlist

sealed class CharacterUI {
    data class Character(
        val id: Int,
        val name: String,
        val image: String,
        val birthdate: String,
    ): CharacterUI()
    data class Header(
        val title: String,
    ): CharacterUI()
    object Button : CharacterUI()
}