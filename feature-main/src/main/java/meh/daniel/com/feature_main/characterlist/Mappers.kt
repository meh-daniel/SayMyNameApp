package meh.daniel.com.feature_main.characterlist

import meh.daniel.com.serial_component.model.Character

internal fun List<Character>.toUI(): List<CharacterUI> {
    return map {
        CharacterUI.Character(
            id = it.id,
            name = it.name,
            image = it.image,
            birthdate = it.birthday,
        )
    }
}