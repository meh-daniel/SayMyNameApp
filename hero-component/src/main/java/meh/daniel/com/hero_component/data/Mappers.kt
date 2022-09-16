package meh.daniel.com.hero_component.data

import meh.daniel.com.hero_component.data.nw.HeroNW
import meh.daniel.com.hero_component.domain.Hero

internal fun List<HeroNW>.toDomain() : List<Hero>{
    return map {
        Hero(
            id = it.charId,
            name = it.name,
            birthday = it.birthday,
            image = it.img,
            status = it.status,
            nickname = it.nickname
        )
    }
}