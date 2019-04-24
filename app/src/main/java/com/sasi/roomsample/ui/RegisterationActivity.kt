package com.sasi.roomsample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sasi.roomsample.R
import com.sasi.roomsample.adapter.CommonListAdapter
import com.sasi.roomsample.adapter.PaggingListAdapter
import com.sasi.roomsample.databinding.ActivityRegisterationBinding
import com.sasi.roomsample.room.tables.UserTable
import com.sasi.roomsample.viewmodel.RegisterationVM

class RegisterationActivity : AppCompatActivity() {

    val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this).get(RegisterationVM::class.java)
    }
    lateinit var binding: ActivityRegisterationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registeration)
        val adapter = PaggingListAdapter()
        binding.rvUsersList.adapter = adapter
        binding.viewmodel = viewModel
        viewModel?.usersList?.observe(this, Observer(adapter::submitList))
    }
}
