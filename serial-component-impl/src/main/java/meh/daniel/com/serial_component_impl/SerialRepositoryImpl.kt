package meh.daniel.com.serial_component_impl

import meh.daniel.com.serial_component.SerialRepository
import meh.daniel.com.serial_component.model.Episode
import meh.daniel.com.serial_component.model.Character
import meh.daniel.com.serial_component.model.CharacterDetails
import meh.daniel.com.serial_component_impl.db.SerialDataBase
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

            for (i in listCharactersOfEpisode.indices){
                val item = correctNameForGet(listCharactersOfEpisode[i])
                val itemGet = api.getHeroByName(item)
                if (itemGet.isNotEmpty()){
                    listHero.add(itemGet[0])
                }
            }

            Episode(
                name = episodeNW[0].title,
                numberEpisode = episodeNW[0].episodeId,
                characters = listHero.toList().toDomain()
            )
        } catch (e: Throwable) {
            Episode(
                "",
                0,
                mutableListOf(
                    Character(0,"","","")
                )
            )
        }

    }

    override suspend fun getHeroDetailsBy(id: Int): CharacterDetails {
        return try {
            api.getHeroById(id)[0].toDomain()
        } catch (e: Throwable) {
            CharacterDetails(0, "", "", "", "", "", "")
        }
    }

    override suspend fun getHeroBy(name: String): List<Character> {
        return try {
            api.getHeroByName(correctNameForGet(name)).toDomain()
        } catch (e: Throwable) {
            mutableListOf(
                Character(0,"","","")
            )
        }
    }

    private fun correctNameForGet(names: String): String {
        return names.replace(' ', '+', true)
    }

}