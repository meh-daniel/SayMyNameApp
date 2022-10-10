package meh.daniel.com.saymynameapp.presentation

import meh.daniel.com.saymynameapp.domain.model.Character
import meh.daniel.com.saymynameapp.presentation.model.CharacterUI

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