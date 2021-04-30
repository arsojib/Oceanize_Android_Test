package com.mysoft.oceanizeandroidtest.di.module

import androidx.lifecycle.ViewModelProvider
import com.mysoft.oceanizeandroidtest.util.viewmodelProvider.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory?): ViewModelProvider.Factory?

}