package com.example.mybank.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mybank.R
import com.example.mybank.databinding.ActivityAddTransactionBinding
import com.example.mybank.models.Transactions
import com.example.mybank.models.TransactionsDAO

class AddTransactionActivity : AppCompatActivity() {

    private lateinit var activityAddTransactionBinding: ActivityAddTransactionBinding // View Binding declaration

    private lateinit var transactionsDAO: TransactionsDAO // DAO declaration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)

        onCreateInitView()
    }

    private fun onCreateInitView() {

        // View binding initialization
        activityAddTransactionBinding = ActivityAddTransactionBinding.inflate(layoutInflater)
        val view = activityAddTransactionBinding.root
        setContentView(view)

        transactionsDAO = TransactionsDAO(this)

        addTransaction()
    }

    private fun addTransaction() {
        activityAddTransactionBinding.saveTransactionBtn.setOnClickListener {

            /*if (activityAddTransactionBinding.transactionTypeProfit.isChecked) {
                activityAddTransactionBinding.transactionAmountEditText.setTextColor(getColor(R.color.green))
            } else if (activityAddTransactionBinding.transactionTypeSpending.isChecked) {
                activityAddTransactionBinding.transactionAmountEditText.setTextColor(getColor(R.color.red))
            }*/

            // Add a transaction
            val transactions = Transactions(-1, activityAddTransactionBinding.transactionAmountEditText.text.toString(),
                (activityAddTransactionBinding.selectedDatePicker.dayOfMonth.toString() + "/" +
                        (activityAddTransactionBinding.selectedDatePicker.month + 1).toString() + "/" +
                        activityAddTransactionBinding.selectedDatePicker.year.toString()))
            transactionsDAO.insert(transactions)

            // Go back to MainActivity finishing AddTaskActivity
            finish()
        }
    }
}