package meh.daniel.com.serial_component_impl

import meh.daniel.com.serial_component.SerialRepository
import meh.daniel.com.serial_component.model.Episode
import meh.daniel.com.serial_component.model.Hero
import meh.daniel.com.serial_component.model.HeroDetails
import meh.daniel.com.serial_component_impl.nw.BreakingBadApi
import meh.daniel.com.serial_component_impl.nw.HeroNW

class SerialRepositoryImpl(
    private val api: BreakingBadApi
) : SerialRepository {

    override suspend fun getEpisode(episode: Int): Episode {
        val episodeNW = api.getEpisode(numberEpisode = episode)
        val listCharactersOfEpisode: List<String> = episodeNW[0].characters
        val listHero = mutableListOf<HeroNW>()

        for (i in listCharactersOfEpisode.indices){
            val item = correctNameForGet(listCharactersOfEpisode[i])
            val itemGet = api.getHeroByName(item)
            if (itemGet.isNotEmpty()){
                listHero.add(itemGet[0])
            }
        }

        return Episode(
            name = episodeNW[0].title,
            numberEpisode = episodeNW[0].episodeId,
            hero = listHero.toList().toDomain()
        )
    }

    override suspend fun getHeroDetailsBy(id: Int): HeroDetails {
        return api.getHeroById(id)[0].toDomain()
    }

    override suspend fun getHeroBy(name: String): List<Hero> {
        return api.getHeroByName(correctNameForGet(name)).toDomain()
    }

    private fun correctNameForGet(names: String): String {
        return names.replace(' ', '+', true)
    }

}