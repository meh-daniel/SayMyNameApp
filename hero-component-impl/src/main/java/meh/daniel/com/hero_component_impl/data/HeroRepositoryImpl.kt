package meh.daniel.com.hero_component_impl.data

import meh.daniel.com.hero_component.domain.Episode
import meh.daniel.com.hero_component.domain.Hero
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

    override suspend fun getBy(id: Int): Hero {
        return api.getById(id)[0].toDomain()
    }

}