package com.sasi.roomsample.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sasi.roomsample.room.tables.UserTable
import com.sasi.roomsample.room.dao.UserInfoDao
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration



@Database(entities = [UserTable::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userModel(): UserInfoDao

    companion object {
        var INSTANCE: AppDatabase? = null
        fun getInMemoryDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "word_database")
                        .addMigrations(MIGRATION_1_2)
                        .build()
                INSTANCE=instance
            }
            return INSTANCE as AppDatabase
        }

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'user_table' ADD COLUMN 'time' TEXT NOT NULL default ''")
            }
        }
    }
}