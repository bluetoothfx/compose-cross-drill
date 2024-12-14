package com.blueprint.composecrossdrill.domain.service

import com.blueprint.composecrossdrill.domain.model.todo.TodoData
import retrofit2.Response
import retrofit2.http.GET

interface LoginService {
    @GET("todos")
    suspend fun getTodos(): Response<TodoData>
}