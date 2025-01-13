package com.blueprint.composecrossdrill.ui.features.dashboard.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.blueprint.composecrossdrill.domain.model.recipes.Recipe
import com.blueprint.composecrossdrill.domain.repository.DashboardRepository
import com.blueprint.composecrossdrill.domain.repository.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

// Note - Here we are passing the repo instance through constructor,
// because we wanted to explore this way of DI too.
// So you can wither access the repo by inject() from KoinComponent
// or declare the viewModelModule separately to provide the dependencies.
class DashboardViewModel(private val dashboardRepository: DashboardRepository) : ViewModel(),
    KoinComponent {

    private val _recipe = mutableStateOf<List<Recipe>>(emptyList())
    val recipe: State<List<Recipe>> = _recipe

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val loginRepository: LoginRepository by inject()

    suspend fun getRecipes() {
        _isLoading.value = true
        _recipe.value = dashboardRepository.getRecipes()
        _isLoading.value = false
    }
}