package meh.daniel.com.hero_component.domain

interface HeroRepository {
    suspend fun getEpisode(episode: Int): Episode
    suspend fun getBy(id: Int): Hero
}