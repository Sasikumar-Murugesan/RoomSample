package com.sasi.roomsample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sasi.roomsample.R
import com.sasi.roomsample.adapter.CommonListAdapter
import com.sasi.roomsample.databinding.ActivityRegisterationBinding
import com.sasi.roomsample.room.tables.UserTable
import com.sasi.roomsample.viewmodel.RegisterationVM

class RegisterationActivity : AppCompatActivity() {
    var viewModel: RegisterationVM? = null
    lateinit var binding: ActivityRegisterationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registeration)
        val adapter = CommonListAdapter()
        binding.rvUsersList.adapter = adapter
        viewModel = ViewModelProviders.of(this).get(RegisterationVM::class.java)
        binding.viewmodel = viewModel
        viewModel?.usersList?.observe(this, Observer {
            for (userTable in it) {
                Log.v("${userTable.id}", "${userTable.username},${userTable.time}")
            }
            adapter.updateUserList(it)
        })

    }
}
