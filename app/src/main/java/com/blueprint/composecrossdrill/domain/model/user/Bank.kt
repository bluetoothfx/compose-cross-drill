package com.blueprint.composecrossdrill.domain.model.user

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bank (

  @SerializedName("cardExpire" ) var cardExpire : String? = null,
  @SerializedName("cardNumber" ) var cardNumber : String? = null,
  @SerializedName("cardType"   ) var cardType   : String? = null,
  @SerializedName("currency"   ) var currency   : String? = null,
  @SerializedName("iban"       ) var iban       : String? = null

): Parcelable