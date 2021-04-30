package com.mysoft.oceanizeandroidtest.data.rest

import android.content.Context
import com.mysoft.oceanizeandroidtest.R
import com.mysoft.oceanizeandroidtest.data.model.CommandInfo
import com.mysoft.oceanizeandroidtest.data.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val context: Context,
    private val apiService: ApiService
) {

    suspend fun getCommands(): Resource<List<CommandInfo>> {
        try {
            val response = withContext(Dispatchers.IO) { apiService.getCommands() }

            return if (response.isSuccessful) {
                Resource.success(response.body()!!)
            } else {
                Resource.error(
                    null, context.getString(
                        R.string.something_went_wrong_please_try_again
                    )
                )
            }
        } catch (e: Exception) {
            return Resource.error(
                null,
                context.getString(R.string.something_went_wrong_please_try_again)
            )
        }
    }

}