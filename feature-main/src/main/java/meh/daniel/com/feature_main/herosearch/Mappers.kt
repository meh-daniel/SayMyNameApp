package meh.daniel.com.feature_main.herosearch

import meh.daniel.com.hero_component.domain.model.Hero

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