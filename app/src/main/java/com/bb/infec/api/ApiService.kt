package com.bb.infec.api

import com.bb.infec.ui.demoData.model.PostDataResponceModel
import com.bb.infec.ui.demoData.model.UserResponceModel
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {
    @GET(Constants.Partials.users)
    fun getUserData(
    ): Observable<UserResponceModel>

    @GET(Constants.Partials.posts)
    fun getPostData(
    ): Observable<PostDataResponceModel>


}


