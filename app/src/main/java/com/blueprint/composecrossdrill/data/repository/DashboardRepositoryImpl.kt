package com.blueprint.composecrossdrill.data.repository

import com.blueprint.composecrossdrill.domain.model.recipes.Recipe
import com.blueprint.composecrossdrill.domain.model.recipes.RecipeUser
import com.blueprint.composecrossdrill.domain.model.user.User
import com.blueprint.composecrossdrill.domain.repository.DashboardRepository
import com.blueprint.composecrossdrill.domain.service.DashboardService
import com.blueprint.composecrossdrill.utils.components.network.ResultWrapper

class DashboardRepositoryImpl(private val dashboardService: DashboardService) :
    DashboardRepository {

    override suspend fun getUsers(): ResultWrapper<List<User>> {
        return try {
            val response = dashboardService.getUsers()
            if (response.isSuccessful) {
                val result =
                    response.body()?.users ?: return ResultWrapper.Failure(null, "No users found")
                ResultWrapper.Success(result)
            } else {
                ResultWrapper.Failure(null, "API Error: ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            ResultWrapper.Failure(e, e.localizedMessage ?: "Unknown error")
        }
    }

    override suspend fun getRecipes(): ResultWrapper<List<Recipe>> {
        return try {
            val response = dashboardService.getRecipes()
            if (response.isSuccessful) {
                val recipes = response.body()?.recipes ?: return ResultWrapper.Failure(
                    null,
                    "No recipes found"
                )
                ResultWrapper.Success(recipes)
            } else {
                ResultWrapper.Failure(null, "API Error: ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            ResultWrapper.Failure(e, e.localizedMessage ?: "Unknown error")
        }
    }

    override suspend fun getRecipesByUser(): ResultWrapper<List<RecipeUser>> {
        return try {
            val usersResult = getUsers()
            val recipesResult = getRecipes()

            if (usersResult is ResultWrapper.Failure) {
                return ResultWrapper.Failure(usersResult.throwable, usersResult.message)
            }

            if (recipesResult is ResultWrapper.Failure) {
                return ResultWrapper.Failure(recipesResult.throwable, recipesResult.message)
            }

            val users = (usersResult as? ResultWrapper.Success)?.data ?: emptyList()
            val recipes = (recipesResult as? ResultWrapper.Success)?.data ?: emptyList()

            val userMap = users.associateBy { it.id }
            val defaultUser = User(id = 0, firstName = "System User")

            val recipeUsers = recipes.map { recipe ->
                val user = userMap[recipe.userId] ?: defaultUser
                RecipeUser(recipe = recipe, user = user)
            }

            ResultWrapper.Success(recipeUsers)
        } catch (e: Exception) {
            ResultWrapper.Failure(
                e,
                e.localizedMessage ?: "Unknown error while fetching recipes by user"
            )
        }
    }
}

