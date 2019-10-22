package com.ez.dotarate.di

import com.ez.dotarate.view.fragments.GameDetailFragment
import com.ez.dotarate.view.fragments.GamesFragment
import com.ez.dotarate.view.fragments.MphFragment
import com.ez.dotarate.view.fragments.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentModule {

    /*
    * We define the name of the Fragment we are going
    * to inject the ViewModelFactory into.
    */
    @ContributesAndroidInjector
    internal abstract fun contributeGamesFragment(): GamesFragment

    @ContributesAndroidInjector
    internal abstract fun contributeMphFragment(): MphFragment

    @ContributesAndroidInjector
    internal abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    internal abstract fun contributeGamesDetailFragment(): GameDetailFragment
}