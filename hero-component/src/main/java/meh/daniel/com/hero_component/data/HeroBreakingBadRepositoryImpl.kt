package meh.daniel.com.hero_component.data

import android.util.Log
import meh.daniel.com.hero_component.data.nw.BreakingBadApi
import meh.daniel.com.hero_component.data.nw.HeroNW
import meh.daniel.com.hero_component.domain.Hero
import meh.daniel.com.hero_component.domain.HeroBreakingBadRepository

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
    override suspend fun getByEpisode(episode: Int): List<Hero> {
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
        listHero.toList()
        return listHero.toDomain()
    }

    override suspend fun getById(id: Int): Hero {
        TODO("Not yet implemented")
    }

    override suspend fun searchByName(name: String): List<Hero> {
        TODO("Not yet implemented")
    }
}