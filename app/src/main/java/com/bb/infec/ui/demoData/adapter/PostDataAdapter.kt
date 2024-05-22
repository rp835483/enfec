package com.bb.infec.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bb.infec.databinding.PostdataItemAdapterBinding
import com.bb.infec.ui.demoData.model.PostDataResponceModel

class PostDataAdapter(var requireContext: Context) : RecyclerView.Adapter<PostDataAdapter.ViewHolder>() {

    var listData=ArrayList<PostDataResponceModel.PostDataResponceItem>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostDataAdapter.ViewHolder {
        val binding =
        PostdataItemAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }
    fun setData( list:ArrayList<PostDataResponceModel.PostDataResponceItem>)
    {
        this.listData=list
    }

    override fun onBindViewHolder(holder: PostDataAdapter.ViewHolder, position: Int) {

        holder.setData(position)

    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ViewHolder(val binding: PostdataItemAdapterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(position: Int) {
            binding.txtName.text=listData[position].title
            binding.txtEmail.text=listData[position].body


        }


    }



}