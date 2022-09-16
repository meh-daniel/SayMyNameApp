package meh.daniel.com.hero_component.data

import meh.daniel.com.hero_component.data.nw.BreakingBadApi
import meh.daniel.com.hero_component.domain.Hero
import meh.daniel.com.hero_component.domain.HeroRepository

class HeroRepositoryImpl(
    private val api: BreakingBadApi
) : HeroRepository {
    override suspend fun getAll(): List<Hero> {
        return api.getAllHero().toDomain()
    }

    override suspend fun getById(id: Int): Hero {
        TODO("Not yet implemented")
    }

    override suspend fun searchByName(name: String): List<Hero> {
        TODO("Not yet implemented")
    }
}