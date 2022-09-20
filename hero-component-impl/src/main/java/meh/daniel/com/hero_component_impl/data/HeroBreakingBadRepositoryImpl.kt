package meh.daniel.com.hero_component_impl.data

import android.util.Log
import meh.daniel.com.hero_component.domain.Episode
import meh.daniel.com.hero_component.domain.Hero
import meh.daniel.com.hero_component.domain.HeroBreakingBadRepository
import meh.daniel.com.hero_component_impl.data.nw.BreakingBadApi
import meh.daniel.com.hero_component_impl.data.nw.HeroNW

class HeroBreakingBadRepositoryImpl(
    private val api: BreakingBadApi
) : HeroBreakingBadRepository {

//    private val name: Map<String, String> = mapOf(
//        "Hank Schrader" to "Henry Schrader"
//    )
//    if(name.containsKey(list[i])){
//        listHero.add(api.getHeroByName(name.getValue(list[i]).replace(' ', '+', true))[0])
//    }
    @Suppress("NAME_SHADOWING")
    override suspend fun getEpisode(episode: Int): Episode {
        val episode = api.getEpisode(numberEpisode = episode)
        val list: List<String> = episode[0].characters
        val listHero = mutableListOf<HeroNW>()
        for (i in list.indices){
            if (api.getHeroByName(list[i].replace(' ', '+', true)).isNotEmpty()){
                listHero.add(api.getHeroByName(list[i].replace(' ', '+', true))[0])
            } else {
                Log.d("xxx", "Not have is ${list[i]}")
            }
        }
        return Episode(
            name = episode[0].title,
            numberEpisode = episode[0].episodeId,
            hero = listHero.toList().toDomain()
        )
    }

    override suspend fun getById(id: Int): Hero {
        return api.getById(id)[0].toDomain()
    }

    override suspend fun searchByName(name: String): List<Hero> {
        TODO("Not yet implemented")
    }
}