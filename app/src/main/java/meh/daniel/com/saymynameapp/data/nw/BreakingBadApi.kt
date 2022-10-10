package meh.daniel.com.saymynameapp.data.nw

import meh.daniel.com.saymynameapp.data.nw.model.EpisodeNW
import meh.daniel.com.saymynameapp.data.nw.model.CharacterNW
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BreakingBadApi {
    @GET("episodes/{episode}")
    suspend fun getEpisode(
        @Path("episode") numberEpisode: Int
    ) : List<EpisodeNW>

    @GET("characters")
    suspend fun getHeroByName(
        @Query("name", encoded = true) name: String
    ) : List<CharacterNW>

    @GET("characters/{id}")
    suspend fun getHeroById(
        @Path("id") id: Int
    ) : List<CharacterNW>
}