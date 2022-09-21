package meh.daniel.com.hero_component_impl.data

import meh.daniel.com.hero_component.domain.Hero
import meh.daniel.com.hero_component_impl.data.nw.HeroNW

internal fun List<HeroNW>.toDomain() : List<Hero>{
    return map {
        Hero(
            id = it.charId,
            name = it.name,
            birthday = it.birthday,
            image = it.img,
            status = it.status,
            nickname = it.nickname,
            portrayed = it.portrayed,
        )
    }
}

internal fun HeroNW.toDomain(): Hero {
    return Hero(
        id = charId,
        name = name,
        image = img,
        birthday = birthday,
        status = status,
        nickname = nickname,
        portrayed = portrayed
    )
}