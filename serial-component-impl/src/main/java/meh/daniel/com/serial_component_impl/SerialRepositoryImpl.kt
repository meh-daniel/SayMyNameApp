package meh.daniel.com.serial_component_impl

import meh.daniel.com.serial_component.SerialRepository
import meh.daniel.com.serial_component.model.Character
import meh.daniel.com.serial_component.model.CharacterDetails
import meh.daniel.com.serial_component.model.Episode
import meh.daniel.com.serial_component_impl.db.SerialDataBase
import meh.daniel.com.serial_component_impl.nw.BreakingBadApi
import meh.daniel.com.serial_component_impl.nw.modelNW.CharacterNW

class SerialRepositoryImpl(
    private val api: BreakingBadApi,
    private val dataBase: SerialDataBase,
) : SerialRepository {

    override suspend fun getEpisode(episode: Int): Episode {
        val episodeNW = api.getEpisode(numberEpisode = episode)
        val listCharactersOfEpisode: List<String> = episodeNW[0].characters
        val listHero = mutableListOf<CharacterNW>()
        val nameEpisode = episodeNW[0].title
        val numberEpisode = episodeNW[0].episodeId

        for (i in listCharactersOfEpisode.indices){
            val item = correctNameForGet(listCharactersOfEpisode[i])
            val itemGet = api.getHeroByName(item)
            if (itemGet.isNotEmpty()){
                val item = itemGet[0]
                listHero.add(item)
            }
        }

        return Episode(
            name = nameEpisode,
            numberEpisode = numberEpisode,
            characters = listHero.toList().toDomainFromNW()
        )
    }

    override suspend fun getCharacterDetailsBy(id: Int): CharacterDetails {
        return api.getHeroById(id)[0].toDomainFromNW()
    }

    override suspend fun getCharacterBy(name: String): List<Character> {
        val character = api.getHeroByName(correctNameForGet(name)).toDomainFromNW()
        for(i in character.indices){
            val item = character[i]
        }
        return character
    }

    private fun correctNameForGet(names: String): String {
        return names.replace(' ', '+', true)
    }

}