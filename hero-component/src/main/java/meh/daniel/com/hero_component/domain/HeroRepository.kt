package meh.daniel.com.hero_component.domain

interface HeroRepository {
    suspend fun getEpisode(episode: Int): Episode
    suspend fun getHeroBy(id: Int): Hero
    suspend fun getHeroBy(name: String): List<Hero>
}