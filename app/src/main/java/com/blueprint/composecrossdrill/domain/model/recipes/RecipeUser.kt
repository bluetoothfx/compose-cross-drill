package com.blueprint.composecrossdrill.domain.model.recipes

import android.os.Parcelable
import com.blueprint.composecrossdrill.domain.model.user.User
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeUser(val recipe: Recipe, val user: User) : Parcelable