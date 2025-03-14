package com.blueprint.composecrossdrill.domain.repository

import com.blueprint.composecrossdrill.domain.model.recipes.Recipe
import com.blueprint.composecrossdrill.domain.model.recipes.RecipeUser
import com.blueprint.composecrossdrill.domain.model.user.User
import com.blueprint.composecrossdrill.utils.components.network.ResultWrapper

interface DashboardRepository {
    suspend fun getUsers(): ResultWrapper<List<User>>
    suspend fun getRecipes(): ResultWrapper<List<Recipe>>
    suspend fun getRecipesByUser(): ResultWrapper<List<RecipeUser>>
}