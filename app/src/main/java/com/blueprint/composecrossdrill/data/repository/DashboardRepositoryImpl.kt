package com.blueprint.composecrossdrill.data.repository

import com.blueprint.composecrossdrill.domain.model.recipes.Recipe
import com.blueprint.composecrossdrill.domain.model.recipes.RecipeUser
import com.blueprint.composecrossdrill.domain.model.user.User
import com.blueprint.composecrossdrill.domain.repository.DashboardRepository
import com.blueprint.composecrossdrill.domain.service.DashboardService

class DashboardRepositoryImpl(private val dashboardService: DashboardService) :
    DashboardRepository {
    override suspend fun getUsers(): List<User> {
        return dashboardService.getUsers().let {
            it.body()?.users as List<User>
        }
    }

    override suspend fun getRecipes(): List<Recipe> {
        return dashboardService.getRecipes().let {
            it.body()?.recipes as List<Recipe>
        }
    }

    override suspend fun getRecipesByUser(): List<RecipeUser> {
        val users = getUsers()
        val recipes = getRecipes()
        val userMap = users.associateBy { it.id }
        val defaultUser = User(id = 0, firstName = "System User")

        return recipes.map { recipe ->
            val user = userMap[recipe.userId] ?: defaultUser
            RecipeUser(recipe = recipe, user = user)
        }
    }


}
