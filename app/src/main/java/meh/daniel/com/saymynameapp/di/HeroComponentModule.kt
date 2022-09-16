package meh.daniel.com.saymynameapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import meh.daniel.com.hero_component.data.HeroRepositoryImpl
import meh.daniel.com.hero_component.data.nw.BreakingBadApi
import meh.daniel.com.hero_component.domain.HeroRepository
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class HeroComponentModule {

    @Provides
    @Singleton
    fun provideWeatherApi(
        @Named("retrofitBreakingBadBuilder") retrofit: Retrofit
    ) : BreakingBadApi {
        return retrofit.create(BreakingBadApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHeroRepository(
        api: BreakingBadApi
    ) : HeroRepository {
        return HeroRepositoryImpl(api)
    }

}