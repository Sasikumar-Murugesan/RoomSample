package com.sasi.roomsample.room.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sasi.roomsample.room.tables.UserTable

@Dao
interface UserInfoDao{
    @Query("select * from user_table")
    fun getTotalUserList():DataSource.Factory<Int,UserTable>

    @Insert
    fun registerUser(userTable:UserTable)
}