package com.blueprint.composecrossdrill.data.repository

import com.blueprint.composecrossdrill.domain.model.todo.TodoData
import com.blueprint.composecrossdrill.domain.repository.LoginRepository
import com.blueprint.composecrossdrill.domain.service.LoginService


class LoginRepositoryImpl(private val loginService: LoginService) : LoginRepository {

    override fun login() {
        TODO("Not yet implemented")
    }

    override suspend fun getTodos() : TodoData {
        return loginService.getTodos().let {
            it.body() as TodoData
        }
    }
}