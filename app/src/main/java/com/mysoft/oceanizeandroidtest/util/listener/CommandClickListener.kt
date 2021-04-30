package com.mysoft.oceanizeandroidtest.util.listener

import com.mysoft.oceanizeandroidtest.data.model.CommandInfo

interface CommandClickListener {

    fun onCommandClick(commandInfo: CommandInfo)

}