package com.sasi.roomsample.room.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sasi.roomsample.room.tables.UserTable

@Dao
interface UserInfoDao{
    @Query("select * from user_table")
    fun getTotalUserList():LiveData<List<UserTable>>

    @Insert
    fun registerUser(userTable:UserTable)
}