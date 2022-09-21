package meh.daniel.com.hero_component_impl.data

import meh.daniel.com.hero_component.domain.model.Episode
import meh.daniel.com.hero_component.domain.model.Hero
import meh.daniel.com.hero_component.domain.model.HeroDetails
import meh.daniel.com.hero_component.domain.HeroRepository
import meh.daniel.com.hero_component_impl.data.nw.BreakingBadApi
import meh.daniel.com.hero_component_impl.data.nw.HeroNW

class HeroRepositoryImpl(
    private val api: BreakingBadApi
) : HeroRepository {

    override suspend fun getEpisode(episode: Int): Episode {
        val episode = api.getEpisode(numberEpisode = episode)
        val list: List<String> = episode[0].characters
        val listHero = mutableListOf<HeroNW>()
        for (i in list.indices){
            if (api.getHeroByName(list[i].replace(' ', '+', true)).isNotEmpty()){
                listHero.add(api.getHeroByName(list[i].replace(' ', '+', true))[0])
            }
        }
        return Episode(
            name = episode[0].title,
            numberEpisode = episode[0].episodeId,
            hero = listHero.toList().toDomain()
        )
    }

    override suspend fun getHeroDetailsBy(id: Int): HeroDetails {
        return api.getHeroById(id)[0].toDomain()
    }

    override suspend fun getHeroBy(name: String): List<Hero> {
        return api.getHeroByName(name.replace(' ', '+', true)).toDomain()
    }

}