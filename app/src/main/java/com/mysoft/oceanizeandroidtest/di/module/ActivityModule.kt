package com.mysoft.oceanizeandroidtest.di.module

import com.mysoft.oceanizeandroidtest.view.MainActivity
import com.mysoft.oceanizeandroidtest.di.scope.CommandScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @CommandScope
    @ContributesAndroidInjector(modules = [CommandModule::class])
    abstract fun contributeMainActivity(): MainActivity

}