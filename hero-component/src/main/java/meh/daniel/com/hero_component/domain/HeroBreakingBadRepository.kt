package meh.daniel.com.hero_component.domain

interface HeroBreakingBadRepository {
    suspend fun getEpisode(episode: Int): Episode
    suspend fun getById(id: Int): Hero
    suspend fun searchByName(name: String): List<Hero>
}