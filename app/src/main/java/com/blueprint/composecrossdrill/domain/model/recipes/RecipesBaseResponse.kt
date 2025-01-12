package com.blueprint.composecrossdrill.domain.model.recipes

import com.google.gson.annotations.SerializedName

data class RecipesBaseResponse(
    @SerializedName("recipes") var recipes: ArrayList<Recipe> = arrayListOf(),
    @SerializedName("total") var total: Int? = null,
    @SerializedName("skip") var skip: Int? = null,
    @SerializedName("limit") var limit: Int? = null
)