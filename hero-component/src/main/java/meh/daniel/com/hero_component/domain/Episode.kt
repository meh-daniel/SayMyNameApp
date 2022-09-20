package meh.daniel.com.hero_component.domain

data class Episode(
    val name: String,
    val numberEpisode: Int,
    val hero: List<Hero>
)