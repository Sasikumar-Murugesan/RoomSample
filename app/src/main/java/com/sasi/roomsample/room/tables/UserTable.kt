package com.sasi.roomsample.room.tables

import androidx.databinding.BaseObservable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "user_table")
public data class UserTable(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int = 0,
        @ColumnInfo(name = "user_name")
        @SerializedName("username")
        var username: String = "",
        @ColumnInfo(name = "gender")
        @SerializedName("gender")
        var gender: String = "",
        @ColumnInfo(name = "created")
        @SerializedName("created")
        var created: String = "",
        @ColumnInfo(name = "location")
        @SerializedName("location")
        var location: String = "",
        @ColumnInfo(name = "time")
        @SerializedName("time")
        var time: String = ""
):Serializable,BaseObservable()

/*
        ,
        @androidx.room.ColumnInfo(name = "time")
        @com.google.gson.annotations.SerializedName("time")
val time: String = ""*/
