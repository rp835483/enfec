package com.bb.infec.ui.demoData

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bb.infec.R
import com.bb.infec.base.BaseActivity
import com.bb.infec.base.EventObserver
import com.bb.infec.databinding.UserdataActivityBinding
import com.bb.infec.extentions.hideProgressDialog
import com.bb.infec.ui.UserDataAdapter
import com.bb.infec.ui.demoData.VModel.UserVM
import com.bb.infec.utils.ErrorUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserDataActivity:BaseActivity<UserdataActivityBinding>() ,UserDataAdapter.ClickCallBack{
    private val userDataVM: UserVM by viewModel()
    lateinit var adapters: UserDataAdapter
    override fun mLayoutRes(): Int {
        return  R.layout.userdata_activity

    }

    override fun onViewReady(savedInstanceState: Bundle?) {

        adapters = UserDataAdapter(this,this)
        userDataVM.getUserData()
        observer()
    }

    private fun observer() {



        //..................................    order status update............................................
        userDataVM.progressIndicator.observe(this, androidx.lifecycle.Observer {
            if (it) {
//                (requireActivity()).showProgressDialog(requireContext(), "Login In")
                com.bb.infec.extentions.showProgressDialog(
                    this,
                    "Please wait.."
                )
            } else {

                hideProgressDialog(this)
            }
        })

        userDataVM.userResponse.observe(this, EventObserver{

            if(it.isNotEmpty())
            {
                mBinding.rvUserData.visibility=View.VISIBLE
                mBinding.nodata.visibility=View.GONE
                adapters.setData(it)
                mBinding.rvUserData.adapter=adapters
            }
            else{
                mBinding.rvUserData.visibility=View.GONE
                mBinding.nodata.visibility=View.VISIBLE
            }


        })

        userDataVM.errorResponse.observe(this, androidx.lifecycle.Observer {
            ErrorUtil.handlerGeneralError(this, mBinding.rvUserData, it)
        })

    }

    override fun ItemClick(id: Int) {
        startActivity(Intent(this, PostDataActivity::class.java))

    }
}