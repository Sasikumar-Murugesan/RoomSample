package com.sasi.roomsample.viewmodel

import android.app.Application
import android.view.View
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.paging.Config
import androidx.paging.PagedList
import com.sasi.roomsample.room.tables.UserTable
import com.sasi.roomsample.room.AppDatabase
import com.sasi.roomsample.room.DbRepository
import com.sasi.roomsample.utils.ObservableViewModel
import com.sasi.roomsample.utils.Utility
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext
import androidx.paging.toLiveData

class RegisterationVM(application: Application) : ObservableViewModel(application) {
    var appDatabase: AppDatabase? = null
    var userTable = UserTable()
    var usersList: LiveData<PagedList<UserTable>>? = null
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
            usersList = dbRepository?.userList?.toLiveData(Config(
                    /**
                     * A good page size is a value that fills at least a screen worth of content on a large
                     * device so the User is unlikely to see a null item.
                     * You can play with this constant to observe the paging behavior.
                     * <p>
                     * It's possible to vary this with list device size, but often unnecessary, unless a
                     * user scrolling on a large device is expected to scroll through items more quickly
                     * than a small device, such as when the large device uses a grid layout of items.
                     */
                    pageSize = 5,

                    /**
                     * If placeholders are enabled, PagedList will report the full size but some items might
                     * be null in onBind method (PagedListAdapter triggers a rebind when data is loaded).
                     * <p>
                     * If placeholders are disabled, onBind will never receive null but as more pages are
                     * loaded, the scrollbars will jitter as new pages are loaded. You should probably
                     * disable scrollbars if you disable placeholders.
                     */
                    enablePlaceholders = true,

                    /**
                     * Maximum number of items a PagedList should hold in memory at once.
                     * <p>
                     * This number triggers the PagedList to start dropping distant pages as more are loaded.
                     */
                    maxSize = 200))
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