package meh.daniel.com.serial_component

import meh.daniel.com.serial_component.model.Episode
import meh.daniel.com.serial_component.model.Character
import meh.daniel.com.serial_component.model.CharacterDetails

interface SerialRepository {
    suspend fun getEpisode(episode: Int): Episode
    suspend fun getCharacterDetailsBy(id: Int): CharacterDetails
    suspend fun getCharacterBy(name: String): List<Character>
}