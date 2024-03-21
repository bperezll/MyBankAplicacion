package com.example.mybank.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.mybank.models.Transactions

class DatabaseManager(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "my_bank.db"
        const val DATABASE_VERSION = 2
        const val COLUMN_NAME_ID = "id"

        private const val SQL_CREATE_TABLE_TRANSACTIONS =
            "CREATE TABLE ${Transactions.TABLE_NAME} (" +
                    "$COLUMN_NAME_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${Transactions.COLUMN_NAME_AMOUNT} TEXT," +
                    "${Transactions.COLUMN_NAME_DATE} TEXT)"

        private const val SQL_DELETE_TABLE_TRANSACTIONS = "DROP TABLE IF EXISTS ${Transactions.TABLE_NAME}"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_TRANSACTIONS)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        destroyDatabase(db)
        onCreate(db)
    }

    private fun destroyDatabase (db: SQLiteDatabase) {
        db.execSQL(SQL_DELETE_TABLE_TRANSACTIONS)
    }
}
