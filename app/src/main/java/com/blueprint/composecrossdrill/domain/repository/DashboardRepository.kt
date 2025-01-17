package com.blueprint.composecrossdrill.domain.repository

import com.blueprint.composecrossdrill.domain.model.recipes.Recipe
import com.blueprint.composecrossdrill.domain.model.recipes.RecipeUser
import com.blueprint.composecrossdrill.domain.model.user.User

interface DashboardRepository {
    suspend fun getUsers(): List<User>
    suspend fun getRecipes(): List<Recipe>
    suspend fun getRecipesByUser(): List<RecipeUser>
}