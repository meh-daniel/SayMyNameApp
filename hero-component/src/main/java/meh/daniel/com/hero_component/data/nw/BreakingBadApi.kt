package meh.daniel.com.hero_component.data.nw

import retrofit2.http.GET

interface BreakingBadApi {

    @GET("characters")
    suspend fun getAllHero() : List<HeroNW>

}