package com.sasi.roomsample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.sasi.roomsample.databinding.RowUserInfoBinding
import com.sasi.roomsample.room.tables.UserTable
import com.sasi.roomsample.viewmodel.RegisterationVM
import com.sasi.roomsample.viewmodel.UserListVM

class CommonListAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var userList: List<UserTable>? = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserVH(RowUserInfoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))

    }

    override fun getItemCount(): Int {
        return userList?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserVH).bindData(userList?.get(position))
    }

    class UserVH(var binding: RowUserInfoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: UserTable?) {
            data?.let {
                binding.viewModel = UserListVM(it)
            }
        }

    }

    fun updateUserList(userInfoList: List<UserTable>) {
        this.userList = userInfoList
        notifyDataSetChanged()
    }

}