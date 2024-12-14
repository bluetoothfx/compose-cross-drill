package com.blueprint.composecrossdrill.domain.model.todo

import com.google.gson.annotations.SerializedName


data class Todo(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("todo") var todo: String? = null,
    @SerializedName("completed") var completed: Boolean? = null,
    @SerializedName("userId") var userId: Int? = null
)