package meh.daniel.com.saymynameapp.data

import meh.daniel.com.saymynameapp.data.db.modelSW.CharacterDetailsSW
import meh.daniel.com.saymynameapp.data.db.modelSW.CharacterSW
import meh.daniel.com.saymynameapp.data.nw.modelNW.CharacterNW
import meh.daniel.com.saymynameapp.domain.model.Character
import meh.daniel.com.saymynameapp.domain.model.CharacterDetails

internal fun List<CharacterNW>.toDomain() : List<Character>{
    return map {
        Character(
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

internal fun CharacterDetailsSW.toDomain(): CharacterDetails {
    return CharacterDetails(
        id = id.toInt(),
        name = name,
        birthday = birthday,
        image = image,
        status = status,
        nickname = nickname,
        portrayed = portrayed,
    )
}

internal fun CharacterNW.toSW(): CharacterDetailsSW {
    return CharacterDetailsSW(
        id = charId.toLong(),
        name = name,
        birthday = birthday,
        image = img,
        status = status,
        nickname = nickname,
        portrayed = portrayed,
    )
}

internal fun List<CharacterNW>.toSW(): List<CharacterSW> {
    return map {
        CharacterSW(
            id = it.charId.toLong(),
            name = it.name,
            image = it.img,
            birthday = it.birthday,
        )
    }
}

internal fun List<CharacterSW>.toDomainFromSW(): List<Character> {
    return map {
        Character(
            id = it.id.toInt(),
            name = it.name,
            image = it.image,
            birthday = it.birthday,
        )
    }
}