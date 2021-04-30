package com.mysoft.oceanizeandroidtest.data.rest

import com.mysoft.oceanizeandroidtest.data.model.CommandInfo
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("ssh")
    suspend fun getCommands(): Response<List<CommandInfo>>

}