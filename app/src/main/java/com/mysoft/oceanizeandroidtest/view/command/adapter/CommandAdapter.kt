package com.mysoft.oceanizeandroidtest.view.command.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mysoft.oceanizeandroidtest.R
import com.mysoft.oceanizeandroidtest.data.model.CommandInfo
import com.mysoft.oceanizeandroidtest.databinding.CommandItemBinding
import com.mysoft.oceanizeandroidtest.util.listener.CommandClickListener

class CommandAdapter(
    private val context: Context,
    private val commandClickListener: CommandClickListener,
    private val list: List<CommandInfo>
) :
    RecyclerView.Adapter<CommandAdapter.ViewHolder>() {

    class ViewHolder(commandItemBinding: CommandItemBinding) :
        RecyclerView.ViewHolder(commandItemBinding.root) {
        val binding = commandItemBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: CommandItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.command_item,
            parent,
            false
        )
        return ViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.commandInfo = list[position]

        holder.binding.root.setOnClickListener { commandClickListener.onCommandClick(list[position]) }
    }

}