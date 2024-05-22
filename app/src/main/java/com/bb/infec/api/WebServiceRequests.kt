package com.bb.infec.api

import com.bb.infec.ui.demoData.model.PostDataResponceModel
import com.bb.infec.ui.demoData.model.UserResponceModel
import io.reactivex.Observable

class WebServiceRequests {
    companion object
    {
        val instanse=WebServiceRequests()
    }

    val apiService by lazy { ApiClient.getClient()!!.create(ApiService::class.java) }



    fun getUserData():Observable<UserResponceModel>
    {
        return apiService!!.getUserData()
    }

     fun getPostData():Observable<PostDataResponceModel>
    {
        return apiService!!.getPostData()
    }


}
