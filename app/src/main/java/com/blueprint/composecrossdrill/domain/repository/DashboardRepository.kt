package com.blueprint.composecrossdrill.domain.repository

import com.blueprint.composecrossdrill.domain.model.User
import com.blueprint.composecrossdrill.domain.model.receipies.Recipes
import java.io.InputStream

interface DashboardRepository {
    suspend fun getUsers(inputStream: InputStream): List<User>
    suspend fun getRecipes(): List<Recipes>
}