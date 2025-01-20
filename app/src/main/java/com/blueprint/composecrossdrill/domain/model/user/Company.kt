package com.blueprint.composecrossdrill.domain.model.user

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Company (

  @SerializedName("department" ) var department : String?  = null,
  @SerializedName("name"       ) var name       : String?  = null,
  @SerializedName("title"      ) var title      : String?  = null,
  @SerializedName("address"    ) var address    : Address? = Address()

): Parcelable