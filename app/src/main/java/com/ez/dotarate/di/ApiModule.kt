package com.ez.dotarate.di

import com.ez.dotarate.constants.BASE_URL_OPENDOTA
import com.ez.dotarate.constants.BASE_URL_STEAM
import com.ez.dotarate.database.AppDatabase
import com.ez.dotarate.model.repository.OpenDotaRepositoryImpl
import com.ez.dotarate.model.repository.UserRepositoryImpl
import com.ez.dotarate.network.ServerApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    internal fun provideUserRepository(@Named("OpenDota") api: ServerApi, db: AppDatabase) =
        UserRepositoryImpl(api, db)

    @Provides
    internal fun provideOpenDotaRepository(@Named("OpenDota") api: ServerApi, db: AppDatabase) =
        OpenDotaRepositoryImpl(api, db)

    @Named("Steam")
    @Provides
    @Singleton
    internal fun provideSteamApi(): ServerApi =
        Retrofit.Builder().baseUrl(BASE_URL_STEAM)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ServerApi::class.java)

    @Named("OpenDota")
    @Provides
    @Singleton
    internal fun provideOpendotaApi(client: OkHttpClient): ServerApi =
        Retrofit.Builder().baseUrl(BASE_URL_OPENDOTA)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().create(ServerApi::class.java)

    @Provides
    @Singleton
    internal fun provideOkHttpClient() = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()
}