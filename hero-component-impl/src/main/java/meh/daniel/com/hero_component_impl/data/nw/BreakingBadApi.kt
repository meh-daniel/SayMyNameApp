package meh.daniel.com.hero_component_impl.data.nw

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
    ) : List<HeroNW>

}