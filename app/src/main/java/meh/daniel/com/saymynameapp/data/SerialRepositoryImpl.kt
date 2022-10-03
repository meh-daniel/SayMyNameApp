package meh.daniel.com.saymynameapp.data

import meh.daniel.com.saymynameapp.data.db.SerialDataBase
import meh.daniel.com.saymynameapp.data.db.modelSW.CharacterSW
import meh.daniel.com.saymynameapp.data.db.modelSW.EpisodeSW
import meh.daniel.com.saymynameapp.data.nw.BreakingBadApi
import meh.daniel.com.saymynameapp.data.nw.modelNW.CharacterNW
import meh.daniel.com.saymynameapp.domain.SerialRepository
import meh.daniel.com.saymynameapp.domain.model.CharacterDetails
import meh.daniel.com.saymynameapp.domain.model.Episode
import meh.daniel.com.saymynameapp.domain.model.Character

class SerialRepositoryImpl(
    private val api: BreakingBadApi,
    private val dataBase: SerialDataBase,
) : SerialRepository {

    override suspend fun getEpisode(episode: Int): Episode = try {
        val episode = dataBase.serialDao().getEpisode(episode.toLong())
        val listCharactersOfEpisode = episode.listIdCharacter.split(" ").toList()
        val listCharacters = mutableListOf<CharacterSW>()

        for(i in listCharactersOfEpisode.indices) {
            if(listCharactersOfEpisode[i].isNotBlank()) {
                listCharacters.add(dataBase.serialDao().getCharacterById(listCharactersOfEpisode[i].toLong()))
            }
        }

        Episode(
            name = episode.name,
            numberEpisode = episode.numberEpisode,
            characters = listCharacters.toDomain()
        )
    } catch (e: Throwable) {
        val episodeNW = api.getEpisode(numberEpisode = episode)
        val nameEpisode = episodeNW.first().title
        val numberEpisode = episodeNW.first().episodeId
        val listCharactersOfEpisode: List<String> = episodeNW.first().characters
        val listCharacters = mutableListOf<CharacterNW>()
        var listId = ""

        for (i in listCharactersOfEpisode.indices){
            val item = correctNameForGetHttp(listCharactersOfEpisode.first())
            val itemGet = api.getHeroByName(item)
            if (itemGet.isNotEmpty()){
                val item = itemGet.first()
                listCharacters.add(item)
                listId += "${item.charId} "
            }
        }

        dataBase.serialDao().insertEpisode(
            EpisodeSW(
                name = nameEpisode,
                numberEpisode = numberEpisode,
                listIdCharacter = listId
            )
        )
        dataBase.serialDao().insertCharacter(listCharacters.toSW())
        Episode(
            name = nameEpisode,
            numberEpisode = numberEpisode,
            characters = listCharacters.toList().toDomain()
        )
    }

    override suspend fun getCharacterDetailsBy(id: Int): CharacterDetails = try {
        dataBase.serialDao().getCharacterDetails(id.toLong()).toDomain()
    } catch (e: Throwable) {
        val characterDetails: CharacterNW = api.getHeroById(id).first()
        dataBase.serialDao().insertCharacterDetails(characterDetails.toSW())
        characterDetails.toDomain()
    }

    override suspend fun getCharacterBy(name: String): List<Character> = try {
        val listCharacter = dataBase.serialDao().findCharacterByName("$name%").toDomain()
        if(listCharacter.isEmpty()) listCharacter else throw Throwable("isEmptyListCharacter")
    } catch (e: Throwable) {
        val character = api.getHeroByName(correctNameForGetHttp(name))
        dataBase.serialDao().insertCharacter(character.toList().toSW())
        character.toDomain()
    }

    private fun correctNameForGetHttp(names: String): String {
        return names.replace(' ', '+', true)
    }
}