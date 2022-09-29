package meh.daniel.com.saymynameapp.domain

import meh.daniel.com.saymynameapp.domain.model.Episode
import meh.daniel.com.saymynameapp.domain.model.Character
import meh.daniel.com.saymynameapp.domain.model.CharacterDetails

interface SerialRepository {
    suspend fun getEpisode(episode: Int): Episode
    suspend fun getCharacterDetailsBy(id: Int): CharacterDetails
    suspend fun getCharacterBy(name: String): List<Character>
}