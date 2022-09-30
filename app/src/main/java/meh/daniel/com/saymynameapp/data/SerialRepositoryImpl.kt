package meh.daniel.com.saymynameapp.data

import android.util.Log
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
    override suspend fun getEpisode(episode: Int): Episode {
        return try {
            val episode = dataBase.serialDao().getEpisode(episode.toLong())
            val listHero = mutableListOf<CharacterSW>()
            val listOf = episode.listIdCharacter.split(" ").toList()

            for(i in listOf.indices) {
                if(listOf[i].isNotBlank()) {
                    listHero.add(dataBase.serialDao().getCharacterById(listOf[i].toLong()))
                }
            }

            Episode(
                name = episode.name,
                numberEpisode = episode.numberEpisode,
                characters = listHero.toDomainCharacterFromSW()
            )
        } catch (e: Throwable) {
            val episodeNW = api.getEpisode(numberEpisode = episode)
            val listCharactersOfEpisode: List<String> = episodeNW[0].characters
            val listHero = mutableListOf<CharacterNW>()
            var listId = ""
            val nameEpisode = episodeNW[0].title
            val numberEpisode = episodeNW[0].episodeId

            for (i in listCharactersOfEpisode.indices){
                val item = correctNameForGetHttp(listCharactersOfEpisode[i])
                val itemGet = api.getHeroByName(item)
                if (itemGet.isNotEmpty()){
                    val item = itemGet[0]
                    listHero.add(item)
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
            dataBase.serialDao().insertCharacter(listHero.toSWCharacterFromNW())
            Episode(
                name = nameEpisode,
                numberEpisode = numberEpisode,
                characters = listHero.toList().toDomainFromNW()
            )
        }
    }
    override suspend fun getCharacterDetailsBy(id: Int): CharacterDetails {
        return try {
            dataBase.serialDao().getCharacterDetails(id.toLong()).toDomainCharacterDetailFromSW()
        } catch (e: Throwable) {
            val characterDetails: CharacterNW = api.getHeroById(id)[0]
            dataBase.serialDao().insertCharacterDetails(characterDetails.toDetailSWFromNW())
            characterDetails.toDomainFromNW()
        }
    }
    override suspend fun getCharacterBy(name: String): List<Character> {
        return try {
            dataBase.serialDao().findCharacterByName("$name%").toDomainCharacterFromSW()
        } catch (e: Throwable) {
            val character = api.getHeroByName(correctNameForGetHttp(name))
            dataBase.serialDao().insertCharacter(character.toList().toSWCharacterFromNW())
            character.toDomainFromNW()
        }
    }
    private fun correctNameForGetHttp(names: String): String {
        return names.replace(' ', '+', true)
    }
}