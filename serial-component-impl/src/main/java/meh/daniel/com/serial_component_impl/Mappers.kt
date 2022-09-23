package meh.daniel.com.serial_component_impl

import meh.daniel.com.serial_component.model.Character
import meh.daniel.com.serial_component.model.CharacterDetails
import meh.daniel.com.serial_component_impl.nw.modelNW.CharacterNW

internal fun List<CharacterNW>.toDomain() : List<Character>{
    return map {
        meh.daniel.com.serial_component.model.Character(
            id = it.charId,
            name = it.name,
            image = it.img,
            birthday = it.birthday,
        )
    }
}

internal fun CharacterNW.toDomain(): CharacterDetails {
    return CharacterDetails(
        id = charId,
        name = name,
        image = img,
        birthday = birthday,
        status = status,
        nickname = nickname,
        portrayed = portrayed,
    )
}