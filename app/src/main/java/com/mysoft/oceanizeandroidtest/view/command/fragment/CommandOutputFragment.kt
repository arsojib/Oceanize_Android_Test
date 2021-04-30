package com.mysoft.oceanizeandroidtest.view.command.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.mysoft.oceanizeandroidtest.R
import com.mysoft.oceanizeandroidtest.base.BaseFragment
import com.mysoft.oceanizeandroidtest.databinding.CommandOutputFragmentLayoutBinding
import com.mysoft.oceanizeandroidtest.util.viewmodelProvider.ViewModelProviderFactory
import com.mysoft.oceanizeandroidtest.view.command.viewmodel.CommandViewModel
import javax.inject.Inject

class CommandOutputFragment : BaseFragment() {

    private lateinit var binding: CommandOutputFragmentLayoutBinding

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    private lateinit var viewModel: CommandViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.command_output_fragment_layout,
            container,
            false
        )
        viewModel = ViewModelProvider(this, providerFactory).get(CommandViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

}