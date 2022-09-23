package meh.daniel.com.serial_component_impl.nw

import meh.daniel.com.serial_component_impl.nw.modelNW.EpisodeNW
import meh.daniel.com.serial_component_impl.nw.modelNW.CharacterNW
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