package com.blueprint.composecrossdrill.data.repository

import com.blueprint.composecrossdrill.domain.model.User
import com.blueprint.composecrossdrill.domain.model.recipes.Recipe
import com.blueprint.composecrossdrill.domain.repository.DashboardRepository
import com.blueprint.composecrossdrill.domain.service.DashboardService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream

class DashboardRepositoryImpl(private val dashboardService: DashboardService) : DashboardRepository {
    override suspend fun getUsers(inputStream: InputStream): List<User> {
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        val gson = Gson()
        val userListType = object : TypeToken<List<User>>() {}.type
        return gson.fromJson(jsonString, userListType)
    }

    override suspend fun getRecipes(): List<Recipe> {
        return dashboardService.getRecipes().let {
            it.body()?.recipes as List<Recipe>
        }
    }
}
