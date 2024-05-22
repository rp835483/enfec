package com.bb.infec.ui.demoData

import android.os.Bundle
import android.view.View
import com.bb.infec.R
import com.bb.infec.base.BaseActivity
import com.bb.infec.base.EventObserver
import com.bb.infec.databinding.PostdataActivityBinding
import com.bb.infec.extentions.hideProgressDialog
import com.bb.infec.ui.PostDataAdapter
import com.bb.infec.ui.demoData.VModel.UserVM
import com.bb.infec.utils.ErrorUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostDataActivity:BaseActivity<PostdataActivityBinding>() {

    private val userDataVM: UserVM by viewModel()
    lateinit var adapters: PostDataAdapter


    override fun mLayoutRes(): Int {
        return  R.layout.postdata_activity

    }

    override fun onViewReady(savedInstanceState: Bundle?) {
        adapters = PostDataAdapter(this)
        userDataVM.getPostData()
        observer()
    }

    private fun observer() {


        mBinding.imgBack.setOnClickListener {
            finish()
        }
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

        userDataVM.postResponse.observe(this, EventObserver{
            if(it.isNotEmpty())
            {
                mBinding.rvPostData.visibility=View.VISIBLE
                mBinding.nodata.visibility=View.GONE
                adapters.setData(it)
                mBinding.rvPostData.adapter=adapters
            }
            else{
                mBinding.rvPostData.visibility=View.GONE
                mBinding.nodata.visibility=View.VISIBLE
            }

        })

        userDataVM.errorResponse.observe(this, androidx.lifecycle.Observer {
            ErrorUtil.handlerGeneralError(this, mBinding.rvPostData, it)
        })

    }
}