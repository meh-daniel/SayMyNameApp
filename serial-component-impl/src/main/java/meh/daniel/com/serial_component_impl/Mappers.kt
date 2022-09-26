package meh.daniel.com.serial_component_impl

import meh.daniel.com.serial_component.model.Character
import meh.daniel.com.serial_component.model.CharacterDetails
import meh.daniel.com.serial_component_impl.db.modelSW.CharacterDetailsSW
import meh.daniel.com.serial_component_impl.db.modelSW.CharacterSW
import meh.daniel.com.serial_component_impl.db.modelSW.EpisodeSwWithCharacterSW
import meh.daniel.com.serial_component_impl.nw.modelNW.CharacterNW

internal fun List<CharacterNW>.toDomainFromNW() : List<Character>{
    return map {
        meh.daniel.com.serial_component.model.Character(
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

internal fun List<CharacterSW>.toDomainFromSW(): List<Character> {
    return map{
        Character(
            id = it.id.toInt(),
            name = it.name,
            image = it.image,
            birthday = it.birthday,
        )
    }
}

internal fun CharacterDetailsSW.toDomainFromSW(): CharacterDetails {
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

internal fun CharacterDetails.toSWFromDomain(): CharacterDetailsSW {
    return CharacterDetailsSW(
        id = id.toLong(),
        name = name,
        birthday = birthday,
        image = image,
        status = status,
        nickname = nickname,
        portrayed = portrayed,
    )
}

internal fun CharacterNW.toSWFromNW(episodeId: Long, characterId: Long): EpisodeSwWithCharacterSW {
    return EpisodeSwWithCharacterSW(
        episodeId = episodeId,
        characterId = characterId,
    )
}

internal fun Character.toSWFromDomain(): CharacterSW {
    return CharacterSW(
        id = id.toLong(),
        name = name,
        image = image,
        birthday = birthday,
    )
}