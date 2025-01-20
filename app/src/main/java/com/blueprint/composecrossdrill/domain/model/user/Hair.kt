package com.blueprint.composecrossdrill.domain.model.user

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hair (

  @SerializedName("color" ) var color : String? = null,
  @SerializedName("type"  ) var type  : String? = null

): Parcelable