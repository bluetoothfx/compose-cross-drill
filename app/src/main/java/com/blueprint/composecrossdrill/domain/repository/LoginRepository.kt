package com.blueprint.composecrossdrill.domain.repository

import com.blueprint.composecrossdrill.domain.model.todo.TodoData

interface LoginRepository {
    fun login()
    suspend fun getTodos() : TodoData
}