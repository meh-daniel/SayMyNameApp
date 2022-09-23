package meh.daniel.com.serial_component

import meh.daniel.com.serial_component.model.Episode
import meh.daniel.com.serial_component.model.Hero
import meh.daniel.com.serial_component.model.HeroDetails

interface SerialRepository {
    suspend fun getEpisode(episode: Int): Episode
    suspend fun getHeroDetailsBy(id: Int): HeroDetails
    suspend fun getHeroBy(name: String): List<Hero>
}