package com.sasi.roomsample.viewmodel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.sasi.roomsample.room.tables.UserTable
import com.sasi.roomsample.utils.ObservableViewModel

class UserListVM(var userTable: UserTable) : ViewModel() {
    val userName = ObservableField<String>(userTable?.username)
}