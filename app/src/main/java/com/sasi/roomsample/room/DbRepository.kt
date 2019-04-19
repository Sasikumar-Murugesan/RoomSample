package com.sasi.roomsample.room

import androidx.annotation.WorkerThread
import com.sasi.roomsample.room.dao.UserInfoDao
import com.sasi.roomsample.room.tables.UserTable

class DbRepository(val userInfoDao: UserInfoDao) {
    var userList = userInfoDao.getTotalUserList()

    @WorkerThread
    suspend fun insert(userTable: UserTable) {
        userInfoDao.registerUser(userTable)
    }
}