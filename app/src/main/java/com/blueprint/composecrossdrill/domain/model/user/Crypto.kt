package com.blueprint.composecrossdrill.domain.model.user


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Crypto (

  @SerializedName("coin"    ) var coin    : String? = null,
  @SerializedName("wallet"  ) var wallet  : String? = null,
  @SerializedName("network" ) var network : String? = null

): Parcelable