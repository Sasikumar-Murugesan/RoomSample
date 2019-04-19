package com.sasi.roomsample.viewmodel

import android.app.Application
import android.view.View
import android.widget.EditText
import androidx.lifecycle.LiveData
import com.sasi.roomsample.room.tables.UserTable
import com.sasi.roomsample.room.AppDatabase
import com.sasi.roomsample.room.DbRepository
import com.sasi.roomsample.utils.ObservableViewModel
import com.sasi.roomsample.utils.Utility
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext


class RegisterationVM(application: Application) : ObservableViewModel(application) {
    var appDatabase: AppDatabase? = null
    var userTable = UserTable()
    var usersList: LiveData<List<UserTable>>? = null
    var dbRepository: DbRepository? = null

    val parentJob = Job()
    // By default all the coroutines launched in this scope should be using the Main dispatcher
    val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)

    init {
        appDatabase = AppDatabase.getInMemoryDatabase(getApplication())
        appDatabase?.userModel()?.let {
            dbRepository = DbRepository(it)
            usersList = dbRepository?.userList
        }
    }

    //save user info to database
    fun onSave(view: View, etUsername: EditText) {
        if (validateUserInfo(view, userTable)) {
            userTable?.time = Calendar.getInstance().time.toString()
            CreateUser(userTable)

        }
    }

    private fun CreateUser(userTable: UserTable) = scope.launch(Dispatchers.IO) {
        dbRepository?.insert(userTable)
        this@RegisterationVM.userTable = UserTable()
        notifyChange()
    }

    fun validateUserInfo(view: View, userTable: UserTable): Boolean {
        if (userTable.username.isNullOrEmpty()) {
            Utility.showShortToast(view.context, "Enter Username")
            return false
        } else if (userTable.username?.length < 2) {
            Utility.showShortToast(view.context, "Username minimum length 2")
            return false
        }
        return true
    }



}