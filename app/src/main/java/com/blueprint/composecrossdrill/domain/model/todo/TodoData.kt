package com.blueprint.composecrossdrill.domain.model.todo

import com.google.gson.annotations.SerializedName


data class TodoData(
    @SerializedName("todos") var todos: ArrayList<Todo> = arrayListOf(),
    @SerializedName("total") var total: Int? = null,
    @SerializedName("skip") var skip: Int? = null,
    @SerializedName("limit") var limit: Int? = null
)