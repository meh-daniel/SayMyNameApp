package meh.daniel.com.hero_component_impl.data

import meh.daniel.com.hero_component.domain.model.Hero
import meh.daniel.com.hero_component.domain.model.HeroDetails
import meh.daniel.com.hero_component_impl.data.nw.HeroNW

internal fun List<HeroNW>.toDomain() : List<Hero>{
    return map {
        Hero(
            id = it.charId,
            name = it.name,
            image = it.img,
            birthday = it.birthday,
        )
    }
}

internal fun HeroNW.toDomain(): HeroDetails {
    return HeroDetails(
        id = charId,
        name = name,
        image = img,
        birthday = birthday,
        status = status,
        nickname = nickname,
        portrayed = portrayed,
    )
}