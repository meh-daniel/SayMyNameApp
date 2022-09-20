package meh.daniel.com.saymynameapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import meh.daniel.com.hero_component.domain.HeroBreakingBadRepository
import meh.daniel.com.hero_component_impl.data.HeroBreakingBadRepositoryImpl
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
    ) : HeroBreakingBadRepository {
        return HeroBreakingBadRepositoryImpl(api)
    }

}