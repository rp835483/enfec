package com.bb.infec.api.baseModel
import com.google.gson.annotations.SerializedName


data class ErrorBean(
    @SerializedName("ErrorDetails")
    val errorDetails: String,
    @SerializedName("ErrorMessage")
    val errorMessage: String ,


//    @SerializedName("Message")
//    val errorMessagess: String
)