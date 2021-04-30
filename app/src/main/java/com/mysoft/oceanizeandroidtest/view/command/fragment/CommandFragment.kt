package com.mysoft.oceanizeandroidtest.view.command.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mysoft.oceanizeandroidtest.R
import com.mysoft.oceanizeandroidtest.base.BaseFragment
import com.mysoft.oceanizeandroidtest.data.model.CommandInfo
import com.mysoft.oceanizeandroidtest.data.model.Resource
import com.mysoft.oceanizeandroidtest.databinding.CommandFragmentLayoutBinding
import com.mysoft.oceanizeandroidtest.util.viewmodelProvider.ViewModelProviderFactory
import com.mysoft.oceanizeandroidtest.util.listener.CommandClickListener
import com.mysoft.oceanizeandroidtest.view.command.adapter.CommandAdapter
import com.mysoft.oceanizeandroidtest.view.command.viewmodel.CommandViewModel
import javax.inject.Inject

class CommandFragment : BaseFragment(), CommandClickListener {

    private lateinit var binding: CommandFragmentLayoutBinding

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    private lateinit var viewModel: CommandViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.command_fragment_layout, container, false
            )
        viewModel = ViewModelProvider(this, providerFactory).get(CommandViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialComponent()
    }

    private fun initialComponent() {
        binding.recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        viewModel.commandList().observe(viewLifecycleOwner, Observer<Resource<List<CommandInfo>>> {
            it?.let { resource ->
                binding.progressBar.visibility = View.GONE
                when (resource.status) {
                    Resource.Status.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        binding.recyclerView.adapter =
                            CommandAdapter(
                                mContext,
                                this,
                                resource.data!!
                            )
                    }
                    else -> {
                        showToast(resource.message!!, Toast.LENGTH_LONG)
                    }
                }
            }
        })
    }

    override fun onCommandClick(commandInfo: CommandInfo) {
        viewModel.executeSSHCommand(commandInfo)
    }

}