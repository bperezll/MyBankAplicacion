package com.example.mybank.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mybank.R
import com.example.mybank.databinding.ActivityAddTransactionBinding
import com.example.mybank.databinding.ActivityHomeBinding
import com.example.mybank.databinding.ItemTransactionsBinding
import com.example.mybank.models.Transactions
import com.example.mybank.models.TransactionsAdapter
import com.example.mybank.models.TransactionsDAO

class HomeActivity : AppCompatActivity() {

    private lateinit var activityHomeBinding: ActivityHomeBinding // View Binding declaration

    private lateinit var transactionsAdapter: TransactionsAdapter // Adapter declaration

    private var transactionsList:MutableList<Transactions> = mutableListOf() // Using Transaction as a Mutable List

    private lateinit var transactionsDAO: TransactionsDAO // DAO declaration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        onCreateInitView()
    }

    private fun onCreateInitView() {

        // DAO initialization
        transactionsDAO = TransactionsDAO(this)

        // View binding initialization
        activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        val view = activityHomeBinding.root
        setContentView(view)

        // Adapter initialization
        transactionsAdapter = TransactionsAdapter(transactionsList)

        // RecyclerView initialization
        activityHomeBinding.transactionsRecyclerView.adapter = transactionsAdapter
        activityHomeBinding.transactionsRecyclerView.layoutManager = LinearLayoutManager(this)

        // Go to AddTransactionActivity
        activityHomeBinding.addNewTransactionFAB.setOnClickListener {
            val intent = Intent(this, AddTransactionActivity::class.java)
            startActivity(intent)
        }

        // Inflate ItemTransactionsBinding
        val activityAddTransactionsBinding = ActivityAddTransactionBinding.inflate(layoutInflater)
        val itemTransactionsBinding = ItemTransactionsBinding.inflate(layoutInflater)

        /*val profit =
            itemTransactionsBinding.transactionAmount.text.contains("+").toString().toFloat()
        val spend =
            itemTransactionsBinding.transactionAmount.text.contains("-").toString().toFloat()

        activityHomeBinding.balanceAmount.text = (profit - spend).toString()*/
    }

    // After add task, go back to this Activity with onResume
    override fun onResume() {
        super.onResume()

        // Finding all transactions on db and showing them
        transactionsList = transactionsDAO.findAll().toMutableList()
        transactionsAdapter.updateItems(transactionsList)
    }
}