package com.bb.infec.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bb.infec.databinding.UserdataItemAdapterBinding
import com.bb.infec.ui.demoData.model.UserResponceModel

class UserDataAdapter(var requireContext: Context,val clickcall: ClickCallBack) : RecyclerView.Adapter<UserDataAdapter.ViewHolder>() {

    var listData=ArrayList<UserResponceModel.UserResponceModelItem>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserDataAdapter.ViewHolder {
        val binding =
        UserdataItemAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }
    fun setData( list:ArrayList<UserResponceModel.UserResponceModelItem>)
    {
        this.listData=list
    }

    override fun onBindViewHolder(holder: UserDataAdapter.ViewHolder, position: Int) {

        holder.setData(position)

    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ViewHolder(val binding: UserdataItemAdapterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(position: Int) {
            binding.txtName.text=listData[position].name
            binding.txtEmail.text=listData[position].email

            binding.cvProcess.setOnClickListener {
                clickcall.ItemClick(listData[position].id)
            }

        }


    }

    interface ClickCallBack {
        fun ItemClick(id: Int)
    }


}