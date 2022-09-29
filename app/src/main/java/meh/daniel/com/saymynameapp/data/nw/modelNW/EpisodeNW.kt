package meh.daniel.com.saymynameapp.data.nw.modelNW


import com.google.gson.annotations.SerializedName

data class EpisodeNW(
    @SerializedName("air_date")
    val airDate: String,
    @SerializedName("characters")
    val characters: List<String>,
    @SerializedName("episode")
    val episode: String,
    @SerializedName("episode_id")
    val episodeId: Int,
    @SerializedName("season")
    val season: String,
    @SerializedName("series")
    val series: String,
    @SerializedName("title")
    val title: String
)