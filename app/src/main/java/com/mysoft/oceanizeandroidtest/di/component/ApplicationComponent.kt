package com.mysoft.oceanizeandroidtest.di.component

import com.mysoft.oceanizeandroidtest.di.module.ActivityModule
import com.mysoft.oceanizeandroidtest.di.module.ApplicationModule
import com.mysoft.oceanizeandroidtest.di.module.NetworkModule
import com.mysoft.oceanizeandroidtest.di.module.ViewModelFactoryModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ApplicationModule::class, ActivityModule::class, ViewModelFactoryModule::class, NetworkModule::class])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

}