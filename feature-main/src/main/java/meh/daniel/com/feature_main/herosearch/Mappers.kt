package meh.daniel.com.feature_main.herosearch

import meh.daniel.com.serial_component.model.Character

internal fun List<Character>.toUI(): List<HeroUI> {
    return map {
        HeroUI.Hero(
            id = it.id,
            name = it.name,
            image = it.image,
            birthdate = it.birthday,
        )
    }
}