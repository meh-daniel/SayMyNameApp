package meh.daniel.com.feature_main.herolist

import meh.daniel.com.hero_component.domain.Hero

internal fun List<Hero>.toUI(): List<HeroUI> {
    return map {
        HeroUI.Hero(
            id = it.id,
            name = it.name,
            image = it.image,
            birthdate = it.birthday,
        )
    }
}