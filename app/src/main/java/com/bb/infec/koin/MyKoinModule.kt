package com.bb.infec.koin


import com.bb.infec.api.WebServiceRequests
import com.bb.infec.ui.demoData.VModel.UserVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { WebServiceRequests() }
    viewModel { UserVM(get()) }


}