package com.bb.infec.ui.demoData.VModel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.bb.infec.api.WebServiceRequests
import com.bb.infec.base.Event
import com.bb.infec.ui.demoData.model.PostDataResponceModel
import com.bb.infec.ui.demoData.model.UserResponceModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserVM(private val webServiceRequests: WebServiceRequests) : ViewModel()  {

    var userResponse = MutableLiveData<Event<UserResponceModel>>()
    var postResponse = MutableLiveData<Event<PostDataResponceModel>>()

    val errorResponse = MutableLiveData<Throwable>()
    val progressIndicator = MutableLiveData<Boolean>()

    @SuppressLint("CheckResult")
    fun getUserData(
    ) {
        progressIndicator.value = true
        webServiceRequests.getUserData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                progressIndicator.value = false
                userResponse.value = Event(it)
            }, {
                progressIndicator.value = false
                errorResponse.value = it
            })

    }
    @SuppressLint("CheckResult")
    fun getPostData(
    ) {
        progressIndicator.value = true
        webServiceRequests.getPostData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                progressIndicator.value = false
                postResponse.value = Event(it)
            }, {
                progressIndicator.value = false
                errorResponse.value = it
            })

    }

}