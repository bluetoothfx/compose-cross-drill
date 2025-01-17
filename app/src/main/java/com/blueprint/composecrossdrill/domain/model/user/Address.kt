package com.blueprint.composecrossdrill.domain.model.user

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Address (

  @SerializedName("address"     ) var address     : String?      = null,
  @SerializedName("city"        ) var city        : String?      = null,
  @SerializedName("state"       ) var state       : String?      = null,
  @SerializedName("stateCode"   ) var stateCode   : String?      = null,
  @SerializedName("postalCode"  ) var postalCode  : String?      = null,
  @SerializedName("coordinates" ) var coordinates : Coordinates? = Coordinates(),
  @SerializedName("country"     ) var country     : String?      = null

): Parcelable