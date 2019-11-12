package com.ez.dotarate.di

import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton

@Module
class NavigationModule {

    @Provides
    @Singleton
    internal fun provideStack() = Stack<NavHostFragment>()

    @Provides
    @Singleton
    internal fun provideLiveNavCOntroller() = MutableLiveData<NavController>()
}