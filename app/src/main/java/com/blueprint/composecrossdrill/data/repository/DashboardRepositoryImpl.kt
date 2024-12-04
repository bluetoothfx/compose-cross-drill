package com.blueprint.composecrossdrill.data.repository

import com.blueprint.composecrossdrill.domain.model.User
import com.blueprint.composecrossdrill.domain.repository.DashboardRepository
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.InputStream

class DashboardRepositoryImpl : DashboardRepository {
    override suspend fun getUsers(inputStream: InputStream): List<User> {
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        val objectMapper = ObjectMapper()
        val userList: List<User> =
            objectMapper.readValue(jsonString, object : TypeReference<List<User>>() {})
        return userList
    }
}