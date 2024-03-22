package com.example.mybank.models

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mybank.R
import com.example.mybank.databinding.ItemTransactionsBinding

class TransactionsAdapter(
    private var items: MutableList<Transactions> = mutableListOf()
    /*val onDeleteItemListener: (position: Int) -> Unit,
    val onItemClickListener: (position: Int) -> Unit,
    val onEditContactClickListener: (position: Int) -> Unit,*/
) : RecyclerView.Adapter<TransactionsAdapter.TransactionsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionsViewHolder {
        val itemTransactionsBinding = ItemTransactionsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionsViewHolder(itemTransactionsBinding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TransactionsViewHolder, position: Int) {
        holder.render(items[position])
        /*holder.binding.deleteContactButton.setOnClickListener { onDeleteItemListener(position) }
        holder.binding.editContactButton.setOnClickListener { onEditContactClickListener(position) }
        holder.binding.itemContactConstraint.setOnClickListener { onItemClickListener(position) }*/
    }

    fun updateItems(results: MutableList<Transactions>) {
        items = results
        notifyDataSetChanged()
    }

    inner class TransactionsViewHolder(val binding:ItemTransactionsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun render(transactions: Transactions) {

            binding.transactionDate.text = transactions.date
            binding.transactionAmount.text = transactions.amount

            if (transactions.amount.toFloat() >= 0) {
                binding.transactionAmount.setTextColor(binding.root.context.getColor(R.color.green))
            } else {
                binding.transactionAmount.setTextColor(binding.root.context.getColor(R.color.red))
            }
        }
    }
}
