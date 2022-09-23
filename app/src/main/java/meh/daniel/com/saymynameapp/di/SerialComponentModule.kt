package meh.daniel.com.saymynameapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import meh.daniel.com.serial_component.SerialRepository
import meh.daniel.com.serial_component_impl.SerialRepositoryImpl
import meh.daniel.com.serial_component_impl.nw.BreakingBadApi
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class SerialComponentModule {

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
    ) : SerialRepository {
        return SerialRepositoryImpl(api)
    }

}