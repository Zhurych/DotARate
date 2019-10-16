package com.ez.dotarate.di

import com.ez.dotarate.constants.BASE_URL
import com.ez.dotarate.model.repository.UserRepositoryImpl
import com.ez.dotarate.network.ServerApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    internal fun provideUserRepository(api: ServerApi) = UserRepositoryImpl(api)

    @Provides
    @Singleton
    internal fun provideServerApi(retrofit: Retrofit): ServerApi {
        return retrofit.create(ServerApi::class.java)
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // TODO: Добавить api с базовым url от opendota.com
}