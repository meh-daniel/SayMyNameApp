package meh.daniel.com.hero_component.domain

import meh.daniel.com.hero_component.domain.model.Episode
import meh.daniel.com.hero_component.domain.model.Hero
import meh.daniel.com.hero_component.domain.model.HeroDetails

interface HeroRepository {
    suspend fun getEpisode(episode: Int): Episode
    suspend fun getHeroDetailsBy(id: Int): HeroDetails
    suspend fun getHeroBy(name: String): List<Hero>
}