package meh.daniel.com.saymynameapp.data

import meh.daniel.com.saymynameapp.data.db.SerialDataBase
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
        return api.getHeroByName(correctNameForGet(name)).toDomainFromNW()
    }

    private fun correctNameForGet(names: String): String {
        return names.replace(' ', '+', true)
    }

}