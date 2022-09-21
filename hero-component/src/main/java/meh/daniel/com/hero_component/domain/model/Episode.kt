package meh.daniel.com.hero_component.domain.model

data class Episode(
    val name: String,
    val numberEpisode: Int,
    val hero: List<Hero>,
)