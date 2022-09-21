package meh.daniel.com.saymynameapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import meh.daniel.com.hero_component.domain.HeroRepository
import meh.daniel.com.hero_component_impl.data.HeroRepositoryImpl
import meh.daniel.com.hero_component_impl.data.nw.BreakingBadApi
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