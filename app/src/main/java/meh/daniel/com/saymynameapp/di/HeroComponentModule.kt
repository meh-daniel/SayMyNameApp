package meh.daniel.com.saymynameapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class HeroComponentModule {

    @Provides
    @Singleton
    fun provideWeatherApi(
        @Named("retrofitBreakingBadBuilder") retrofit: Retrofit
    ) : meh.daniel.com.serial_component_impl.nw.BreakingBadApi {
        return retrofit.create(meh.daniel.com.serial_component_impl.nw.BreakingBadApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHeroRepository(
        api: meh.daniel.com.serial_component_impl.nw.BreakingBadApi
    ) : meh.daniel.com.serial_component.SerialRepository {
        return meh.daniel.com.serial_component_impl.SerialRepositoryImpl(api)
    }

}