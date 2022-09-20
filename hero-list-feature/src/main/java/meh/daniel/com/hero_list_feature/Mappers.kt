package meh.daniel.com.hero_list_feature

import meh.daniel.com.hero_component.domain.Hero

internal fun List<Hero>.toUI(): List<HeroUI> {
    return map {
        HeroUI.Hero(
            name = it.name,
            image = it.image
        )
    }
}