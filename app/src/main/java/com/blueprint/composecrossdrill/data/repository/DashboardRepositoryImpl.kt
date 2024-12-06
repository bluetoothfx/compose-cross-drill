package com.blueprint.composecrossdrill.data.repository

import com.blueprint.composecrossdrill.domain.model.User
import com.blueprint.composecrossdrill.domain.repository.DashboardRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream

class DashboardRepositoryImpl : DashboardRepository {
    override suspend fun getUsers(inputStream: InputStream): List<User> {
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        val gson = Gson()
        val userListType = object : TypeToken<List<User>>() {}.type
        return gson.fromJson(jsonString, userListType)
    }
}
