package meh.daniel.com.serial_component.model

data class Episode(
    val name: String,
    val numberEpisode: Int,
    val hero: List<Hero>,
)