package com.example.mybank.models

import com.example.mybank.utils.DatabaseManager

class Transactions (var id: Int, var amount: String, var date: String) {

    companion object {
        const val TABLE_NAME = "Transactions"
        const val COLUMN_NAME_AMOUNT = "amount"
        const val COLUMN_NAME_DATE = "date"
        val COLUMN_NAMES = arrayOf(
            DatabaseManager.COLUMN_NAME_ID,
            COLUMN_NAME_AMOUNT,
            COLUMN_NAME_DATE,
        )
    }
}
