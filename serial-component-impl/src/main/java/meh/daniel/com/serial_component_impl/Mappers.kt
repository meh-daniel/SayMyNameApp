package meh.daniel.com.serial_component_impl

import meh.daniel.com.serial_component.model.Hero
import meh.daniel.com.serial_component.model.HeroDetails
import meh.daniel.com.serial_component_impl.nw.HeroNW

internal fun List<HeroNW>.toDomain() : List<Hero>{
    return map {
        meh.daniel.com.serial_component.model.Hero(
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