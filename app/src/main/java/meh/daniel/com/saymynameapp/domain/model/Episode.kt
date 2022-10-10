package meh.daniel.com.saymynameapp.domain.model

data class Episode(
    val name: String,
    val numberEpisode: Int,
    val characters: List<Character>,
)