package com.bb.infec.ui.demoData.model


import com.google.gson.annotations.SerializedName

class PostDataResponceModel : ArrayList<PostDataResponceModel.PostDataResponceItem>(){
    data class PostDataResponceItem(
        @SerializedName("body")
        var body: String,
        @SerializedName("id")
        var id: Int,
        @SerializedName("title")
        var title: String,
        @SerializedName("userId")
        var userId: Int
    )
}