package com.blueprint.composecrossdrill.domain.service

import com.blueprint.composecrossdrill.domain.model.recipes.RecipesBaseResponse
import com.blueprint.composecrossdrill.domain.model.user.UserDataResponse
import retrofit2.Response
import retrofit2.http.GET

interface DashboardService {
    @GET("recipes")
    suspend fun getRecipes(): Response<RecipesBaseResponse>

    @GET("users")
    suspend fun getUsers(): Response<UserDataResponse>
}