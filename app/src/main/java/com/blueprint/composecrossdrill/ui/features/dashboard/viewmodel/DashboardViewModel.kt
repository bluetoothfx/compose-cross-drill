package com.blueprint.composecrossdrill.ui.features.dashboard.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.blueprint.composecrossdrill.domain.model.User
import com.blueprint.composecrossdrill.domain.model.receipies.Recipes
import com.blueprint.composecrossdrill.domain.model.todo.TodoData
import com.blueprint.composecrossdrill.domain.repository.DashboardRepository
import com.blueprint.composecrossdrill.domain.repository.LoginRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.InputStream

class DashboardViewModel(private val dashboardRepository: DashboardRepository) : ViewModel(),
    KoinComponent {

    private val _users = mutableStateOf<List<User>>(emptyList())
    val users: State<List<User>> = _users

    private val _userTodos = mutableStateOf(TodoData())
    val userTodos: State<TodoData> = _userTodos

    private val _recipes = mutableStateOf<List<Recipes>>(emptyList())
    val recipes: State<List<Recipes>> = _recipes

    private val loginRepository: LoginRepository by inject()

    suspend fun getUsers(inputStream: InputStream) {
        _users.value = dashboardRepository.getUsers(inputStream)
    }

    suspend fun getUserTodos() {
        _userTodos.value = loginRepository.getTodos()
    }

    suspend fun getRecipes() {
        _recipes.value = dashboardRepository.getRecipes()
    }
}