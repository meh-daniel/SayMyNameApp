package meh.daniel.com.serial_component_impl

import kotlin.properties.Delegates
import kotlinx.coroutines.flow.map
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
            var name by Delegates.notNull<String>()
            var numberEpisode by Delegates.notNull<Int>()
            val listHero = mutableListOf<Int>()

            dataBase.serialDao().getEpisode(episode.toLong()).map { entity ->
                entity.map {
                    name = it.key.name
                    numberEpisode = it.key.numberEpisode
                    listHero.add(it.value.characterId.toInt())
                }
            }
            Episode(
                name = name,
                numberEpisode = numberEpisode,
                characters = listHero.map {
                    Character(
                        id = it,
                        name = "123",
                        image = "null",
                        birthday = "123",
                    )
                }
            )
            throw Throwable()
        }
    }

    override suspend fun getCharacterDetailsBy(id: Int): CharacterDetails {
        return try {
            val details = api.getHeroById(id)[0].toDomainFromNW()
            dataBase.serialDao().insertCharacterDetails(details.toSWFromDomain())
            details
        } catch (e: Throwable) {
            dataBase.serialDao().getCharacterDetails(id.toLong()).toDomainFromSW()
            throw Throwable()
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
            throw Throwable()
        }
    }

    private fun correctNameForGet(names: String): String {
        return names.replace(' ', '+', true)
    }

}