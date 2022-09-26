package meh.daniel.com.serial_component_impl

import meh.daniel.com.serial_component.SerialRepository
import meh.daniel.com.serial_component.model.Episode
import meh.daniel.com.serial_component.model.Character
import meh.daniel.com.serial_component.model.CharacterDetails
import meh.daniel.com.serial_component_impl.db.SerialDataBase
import meh.daniel.com.serial_component_impl.db.modelSW.CharacterDetailsSW
import meh.daniel.com.serial_component_impl.db.modelSW.CharacterSW
import meh.daniel.com.serial_component_impl.db.modelSW.EpisodeSW
import meh.daniel.com.serial_component_impl.db.modelSW.EpisodeSwWithCharacterSW
import meh.daniel.com.serial_component_impl.nw.BreakingBadApi
import meh.daniel.com.serial_component_impl.nw.modelNW.CharacterNW

class SerialRepositoryImpl(
    private val api: BreakingBadApi,
    private val dataBase: SerialDataBase,
) : SerialRepository {

    override suspend fun getEpisode(episode: Int): Episode {
        return try {
            val episodeNW = api.getEpisode(numberEpisode = episode)
            val listCharactersOfEpisode: List<String> = episodeNW[0].characters
            val listHero = mutableListOf<CharacterNW>()
            val nameEpisode = episodeNW[0].title
            val numberEpisode = episodeNW[0].episodeId

            dataBase.serialDao().insertEpisode(EpisodeSW(name = nameEpisode, numberEpisode = numberEpisode))
            for (i in listCharactersOfEpisode.indices){
                val item = correctNameForGet(listCharactersOfEpisode[i])
                val itemGet = api.getHeroByName(item)
                if (itemGet.isNotEmpty()){
                    val item = itemGet[0]
                    listHero.add(item)
                    dataBase.serialDao().insertEpisodeWithCharacter(item.toSWFromNW(numberEpisode.toLong(), item.charId.toLong()))
                }
            }

            Episode(
                name = nameEpisode,
                numberEpisode = numberEpisode,
                characters = listHero.toList().toDomainFromNW()
            )
        } catch (e: Throwable) {
            val data = dataBase.serialDao().getEpisode(episode.toLong())
            Episode(
                "",
                0,
                mutableListOf(
                    Character(0,"","","")
                )
            )
        }
    }

    override suspend fun getCharacterDetailsBy(id: Int): CharacterDetails {
        return try {
            val details = api.getHeroById(id)[0].toDomainFromNW()
            dataBase.serialDao().insertCharacterDetails(details.toSWFromDomain())
            details
        } catch (e: Throwable) {
            dataBase.serialDao().getCharacterDetails(id.toLong()).toDomainFromSW()
        }
    }

    override suspend fun getCharacterBy(name: String): List<Character> {
        return try {
            val character = api.getHeroByName(correctNameForGet(name)).toDomainFromNW()
            for(i in character.indices){
                val item = character[i]
                dataBase.serialDao().insertCharacter(item.toSWFromDomain())
            }
            character
        } catch (e: Throwable) {
            dataBase.serialDao().findCharacterByName(name).toDomainFromSW()
        }
    }

    private fun correctNameForGet(names: String): String {
        return names.replace(' ', '+', true)
    }

}