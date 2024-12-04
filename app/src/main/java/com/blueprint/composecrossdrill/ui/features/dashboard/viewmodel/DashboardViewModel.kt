package com.blueprint.composecrossdrill.ui.features.dashboard.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.blueprint.composecrossdrill.domain.model.User
import com.blueprint.composecrossdrill.domain.repository.DashboardRepository
import java.io.InputStream

class DashboardViewModel(private val dashboardRepository: DashboardRepository) : ViewModel() {

    private val _users = mutableStateOf<List<User>>(emptyList())
    val users: State<List<User>> = _users

    suspend fun getUsers(inputStream: InputStream){
        _users.value = dashboardRepository.getUsers(inputStream)
    }
}