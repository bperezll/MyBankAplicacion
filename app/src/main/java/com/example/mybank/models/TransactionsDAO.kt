package com.example.mybank.models

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.mybank.models.Transactions
import com.example.mybank.utils.DatabaseManager

class TransactionsDAO (context: Context) {  //(val context: Context) {

    private var databaseManager: DatabaseManager = DatabaseManager(context)

    fun insert(transactions: Transactions): Transactions {
        val db = databaseManager.writableDatabase

        val values = ContentValues()
        values.put(Transactions.COLUMN_NAME_AMOUNT, transactions.amount)
        values.put(Transactions.COLUMN_NAME_DATE, transactions.date)

        val newRowId = db.insert(Transactions.TABLE_NAME, null, values)
        Log.i("DATABASE", "New record id: $newRowId")

        db.close()

        transactions.id = newRowId.toInt()
        return transactions
    }

    fun update(transactions: Transactions) {
        val db = databaseManager.writableDatabase

        val values = ContentValues()
        values.put(Transactions.COLUMN_NAME_AMOUNT, transactions.amount)
        values.put(Transactions.COLUMN_NAME_DATE, transactions.date)

        val updatedRows = db.update(Transactions.TABLE_NAME, values, "${DatabaseManager.COLUMN_NAME_ID} = ${transactions.id}", null)
        Log.i("DATABASE", "Updated records: $updatedRows")

        db.close()
    }

    fun delete(transactions: Transactions) {
        val db = databaseManager.writableDatabase

        val deletedRows = db.delete(Transactions.TABLE_NAME, "${DatabaseManager.COLUMN_NAME_ID} = ${transactions.id}", null)
        Log.i("DATABASE", "Deleted rows: $deletedRows")

        db.close()
    }

    @SuppressLint("Range")
    fun find(id: Int): Transactions? {
        val db = databaseManager.writableDatabase

        val cursor = db.query(
            Transactions.TABLE_NAME,                         // The table to query
            Transactions.COLUMN_NAMES,       // The array of columns to return (pass null to get all)
            "${DatabaseManager.COLUMN_NAME_ID} = $id",                        // The columns for the WHERE clause
            null,                    // The values for the WHERE clause
            null,                        // don't group the rows
            null,                         // don't filter by row groups
            null                         // The sort order
        )

        var transactions: Transactions? = null

        if (cursor.moveToNext()) {
            val transactionsId = cursor.getInt(cursor.getColumnIndex(DatabaseManager.COLUMN_NAME_ID))
            val transactionsAmount = cursor.getString(cursor.getColumnIndex(Transactions.COLUMN_NAME_AMOUNT))
            val transactionsDate = cursor.getString(cursor.getColumnIndex(Transactions.COLUMN_NAME_DATE))

            transactions = Transactions(transactionsId, transactionsAmount, transactionsDate)
        }

        cursor.close()
        db.close()

        return transactions
    }

    @SuppressLint("Range")
    fun findAll(): MutableList<Transactions> {
        val db = databaseManager.writableDatabase

        val cursor = db.query(
            Transactions.TABLE_NAME,                 // The table to query
            Transactions.COLUMN_NAMES,     // The array of columns to return (pass null to get all)
            null,                // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            "${Transactions.COLUMN_NAME_DATE} GLOB '[A-Za-z]*' DESC, ${Transactions.COLUMN_NAME_DATE}" // Sort alphabetically
        )

        val list: MutableList<Transactions> = mutableListOf()

        while (cursor.moveToNext()) {
            val transactionsId = cursor.getInt(cursor.getColumnIndex(DatabaseManager.COLUMN_NAME_ID))
            val transactionsAmount = cursor.getString(cursor.getColumnIndex(Transactions.COLUMN_NAME_AMOUNT))
            val transactionsDate = cursor.getString(cursor.getColumnIndex(Transactions.COLUMN_NAME_DATE))

            val transactions = Transactions(transactionsId, transactionsAmount, transactionsDate)
            list.add(transactions)
        }

        cursor.close()
        db.close()

        return list
    }
}
