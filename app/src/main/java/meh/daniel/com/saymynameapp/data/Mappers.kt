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


@JvmName("toDomainCharacterSW")
internal fun List<CharacterSW>.toDomain(): List<Character> {
    return map {
        Character(
            id = it.id.toInt(),
            name = it.name,
            image = it.image,
            birthday = it.birthday,
        )
    }
}


internal fun CharacterSW.toDomain(): Character {
    return Character(
        id = id.toInt(),
        name = name,
        image = image,
        birthday = birthday
    )
}

@JvmName("toSWCharacter")
internal fun List<Character>.toSW(): List<CharacterSW> {
    return map {
        CharacterSW(
            id = it.id.toLong(),
            name = it.name,
            image = it.image,
            birthday = it.birthday
        )
    }
}

internal fun MutableList<Character>.toSW(): String {
    var id = ""
    map {
        id += "${it.id} "
    }
    return id
}

internal fun List<CharacterNW>.toDomainOne(): Character {
    return Character(
        id = first().charId,
        name = first().name,
        image = first().img,
        birthday = first().birthday,
    )
}