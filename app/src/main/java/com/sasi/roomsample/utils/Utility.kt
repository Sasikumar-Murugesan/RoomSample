package com.sasi.roomsample.utils

import android.content.Context
import android.widget.Toast

object Utility {
    fun showShortToast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}