package com.mysoft.oceanizeandroidtest.di.module

import androidx.lifecycle.ViewModel
import com.mysoft.oceanizeandroidtest.view.command.fragment.CommandFragment
import com.mysoft.oceanizeandroidtest.view.command.fragment.CommandOutputFragment
import com.mysoft.oceanizeandroidtest.view.command.viewmodel.CommandViewModel
import com.mysoft.oceanizeandroidtest.di.scope.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class CommandModule {

    @ContributesAndroidInjector
    abstract fun contributeCommandFragment(): CommandFragment

    @ContributesAndroidInjector
    abstract fun contributeCommandOutputFragment(): CommandOutputFragment

    @Binds
    @IntoMap
    @ViewModelKey(CommandViewModel::class)
    abstract fun bindCommandViewModel(viewModel: CommandViewModel?): ViewModel?

}