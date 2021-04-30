package com.mysoft.oceanizeandroidtest.base

import android.content.Context
import android.widget.Toast
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment() {

    lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    fun showToast(message: String, duration: Int) {
        Toast.makeText(context, message, duration).show()
    }

}