package meh.daniel.com.hero_component.domain

interface HeroBreakingBadRepository {
    suspend fun getByEpisode(episode: Int): List<Hero>
    suspend fun getById(id: Int): Hero
    suspend fun searchByName(name: String): List<Hero>
}