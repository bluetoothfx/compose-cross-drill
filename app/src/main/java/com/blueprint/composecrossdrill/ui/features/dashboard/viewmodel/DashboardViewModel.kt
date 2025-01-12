package com.blueprint.composecrossdrill.ui.features.dashboard.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.blueprint.composecrossdrill.domain.model.User
import com.blueprint.composecrossdrill.domain.model.recipes.Recipe
import com.blueprint.composecrossdrill.domain.model.todo.TodoData
import com.blueprint.composecrossdrill.domain.repository.DashboardRepository
import com.blueprint.composecrossdrill.domain.repository.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.InputStream

class DashboardViewModel(private val dashboardRepository: DashboardRepository) : ViewModel(),
    KoinComponent {

    private val _users = mutableStateOf<List<User>>(emptyList())
    val users: State<List<User>> = _users

    private val _userTodos = mutableStateOf(TodoData())
    val userTodos: State<TodoData> = _userTodos

    private val _recipe = mutableStateOf<List<Recipe>>(emptyList())
    val recipe: State<List<Recipe>> = _recipe

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val loginRepository: LoginRepository by inject()

    suspend fun getUsers(inputStream: InputStream) {
        _users.value = dashboardRepository.getUsers(inputStream)
    }

    suspend fun getUserTodos() {
        _userTodos.value = loginRepository.getTodos()
    }

    suspend fun getRecipes() {
        _isLoading.value = true
        _recipe.value = dashboardRepository.getRecipes()
        _isLoading.value = false
    }
}