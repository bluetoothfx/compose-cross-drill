package com.blueprint.composecrossdrill.domain.model.user


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDataResponse(

    @SerializedName("users") var users: ArrayList<User> = arrayListOf(),
    @SerializedName("total") var total: Int? = null,
    @SerializedName("skip") var skip: Int? = null,
    @SerializedName("limit") var limit: Int? = null

): Parcelable