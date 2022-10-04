package meh.daniel.com.saymynameapp.data

import meh.daniel.com.saymynameapp.data.db.SerialDataBase
import meh.daniel.com.saymynameapp.data.db.model.EpisodeSW
import meh.daniel.com.saymynameapp.data.nw.BreakingBadApi
import meh.daniel.com.saymynameapp.data.nw.model.CharacterNW
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
        val characters = mutableListOf<Character>()
        episode.listIdCharacter.split(" ").toList().map { StringOfId ->
            if(StringOfId.isNotBlank()) characters.add(dataBase.serialDao().getCharacterById(StringOfId.toLong()).toDomain())
        }
        Episode(
            name = episode.name,
            numberEpisode = episode.numberEpisode,
            characters = characters,
        )
    } catch (e: Throwable) {
        val episode = api.getEpisode(numberEpisode = episode)
        val nameEpisode = episode.first().title
        val numberEpisode = episode.first().episodeId
        val charactersEpisode: List<String> = episode.first().characters

        val characters = mutableListOf<Character>()
        for (i in charactersEpisode.indices){
            val item = correctNameForGetHttp(charactersEpisode[i])
            val itemGet = api.getHeroByName(item)
            if (itemGet.isNotEmpty()){
                characters.add(itemGet.toDomainOne())
            }
        }

        dataBase.serialDao().insertCharacter(characters.toList().toSW())
        dataBase.serialDao().insertEpisode(
            EpisodeSW(
                name = nameEpisode,
                numberEpisode = numberEpisode,
                listIdCharacter = characters.toSW()
            )
        )
        Episode(
            name = nameEpisode,
            numberEpisode = numberEpisode,
            characters = characters.toList(),
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




