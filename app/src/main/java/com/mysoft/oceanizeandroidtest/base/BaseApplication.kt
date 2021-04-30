package com.mysoft.oceanizeandroidtest.base

import com.mysoft.oceanizeandroidtest.di.component.DaggerApplicationComponent
import com.mysoft.oceanizeandroidtest.di.module.ApplicationModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
    }
}