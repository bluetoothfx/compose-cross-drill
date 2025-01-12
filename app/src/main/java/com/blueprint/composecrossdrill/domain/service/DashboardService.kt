package com.blueprint.composecrossdrill.domain.service

import com.blueprint.composecrossdrill.domain.model.receipies.RecipesBaseResponse
import retrofit2.Response
import retrofit2.http.GET

interface DashboardService {
    @GET("recipes")
    suspend fun getRecipes(): Response<RecipesBaseResponse>
}