package com.sasi.roomsample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sasi.roomsample.databinding.RowUserInfoBinding
import com.sasi.roomsample.room.tables.UserTable

class PaggingListAdapter : PagedListAdapter<UserTable, RecyclerView.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CommonListAdapter.UserVH(RowUserInfoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CommonListAdapter.UserVH).bindTo(getItem(position))
    }


    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<UserTable>(){
            override fun areItemsTheSame(oldItem: UserTable, newItem: UserTable): Boolean =
                    oldItem.id == newItem.id

            /**
             * Note that in kotlin, == checking on data classes compares all contents, but in Java,
             * typically you'll implement Object#equals, and use it to compare object contents.
             */
            override fun areContentsTheSame(oldItem: UserTable, newItem: UserTable): Boolean =
                    oldItem == newItem

        }
    }

}