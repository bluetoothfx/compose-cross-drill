package com.blueprint.composecrossdrill.ui.features.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import com.blueprint.composecrossdrill.domain.model.recipes.Recipe
import com.blueprint.composecrossdrill.domain.model.recipes.RecipeUser
import com.blueprint.composecrossdrill.domain.model.user.User
import com.blueprint.composecrossdrill.domain.repository.DashboardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent

// Note - Here we are passing the repo instance through constructor,
// because we wanted to explore this way of DI too.
// So you can wither access the repo by inject() from KoinComponent
// or declare the viewModelModule separately to provide the dependencies.
class DashboardViewModel(private val dashboardRepository: DashboardRepository) : ViewModel(),
    KoinComponent {

    private val _recipe = MutableStateFlow<List<Recipe>>(emptyList())
    val recipe: StateFlow<List<Recipe>> = _recipe

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    private val _recipeUser = MutableStateFlow<List<RecipeUser>>(emptyList())
    val recipeUser: StateFlow<List<RecipeUser>> = _recipeUser

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    suspend fun getRecipes() {
        _isLoading.value = true
        _recipe.value = dashboardRepository.getRecipes()
        _isLoading.value = false
    }

    suspend fun getUsers() {
        _isLoading.value = true
        _users.value = dashboardRepository.getUsers()
        _isLoading.value = false
    }

    suspend fun getRecipeUser() {
        _isLoading.value = true
        _recipeUser.value = dashboardRepository.getRecipesByUser()
        _isLoading.value = false
    }
}