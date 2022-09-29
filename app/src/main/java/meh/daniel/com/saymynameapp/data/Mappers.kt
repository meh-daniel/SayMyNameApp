package meh.daniel.com.saymynameapp.data

import meh.daniel.com.saymynameapp.data.nw.modelNW.CharacterNW
import meh.daniel.com.saymynameapp.domain.model.Character
import meh.daniel.com.saymynameapp.domain.model.CharacterDetails

internal fun List<CharacterNW>.toDomainFromNW() : List<Character>{
    return map {
        Character(
            id = it.charId,
            name = it.name,
            image = it.img,
            birthday = it.birthday,
        )
    }
}

internal fun CharacterNW.toDomainFromNW(): CharacterDetails {
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