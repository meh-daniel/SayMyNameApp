package meh.daniel.com.saymynameapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import meh.daniel.com.saymynameapp.domain.SerialRepository
import meh.daniel.com.saymynameapp.data.SerialRepositoryImpl
import meh.daniel.com.saymynameapp.data.db.SerialDataBase
import meh.daniel.com.saymynameapp.data.nw.BreakingBadApi
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class SerialComponentModule {

    @Provides
    @Singleton
    fun provideBreakingBadApi(
        @Named("retrofitBreakingBadBuilder") retrofit: Retrofit
    ) : BreakingBadApi {
        return retrofit.create(BreakingBadApi::class.java)
    }

    @Provides
    @Singleton
    fun createSerialDataBase(
        @ApplicationContext context: Context
    ): SerialDataBase {
        return SerialDataBase.invoke(context)
    }

    @Provides
    @Singleton
    fun provideSerialRepository(
        api: BreakingBadApi,
        dataBase: SerialDataBase
    ) : SerialRepository {
        return SerialRepositoryImpl(api, dataBase)
    }

}