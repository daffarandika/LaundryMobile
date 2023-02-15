package com.example.laundrymobile.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.laundrymobile.R
import com.example.laundrymobile.ViewServiceActivity
import com.example.laundrymobile.model.Deposit
import com.example.laundrymobile.utils.Consts
import com.example.laundrymobile.utils.Consts.Companion.services

class DepositAdapter(
    val context: Context,
    val deposits: MutableList<Deposit>
): RecyclerView.Adapter<DepositAdapter.DViewHolder>() {
    inner class DViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvCustomer: TextView = view.findViewById(R.id.tvCustomer)
        val tvEmployee: TextView = view.findViewById(R.id.tvEmployee)
        val tvTransactionDateTime: TextView = view.findViewById(R.id.tvTransactionDateTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.deposit_row_item, parent, false)
        return DViewHolder(view)
    }

    override fun getItemCount(): Int {
        return deposits.size
    }

    override fun onBindViewHolder(holder: DViewHolder, position: Int) {
        val deposit = deposits[position]
        holder.tvCustomer.text = "Customer : ${deposit.customer}"
        holder.tvEmployee.text = "Employee : ${deposit.employee}"
        holder.tvTransactionDateTime.text = "Date : ${deposit.transactionDateTime}"
        holder.itemView.setOnClickListener{
            services = deposit.services
            val intent = Intent(context, ViewServiceActivity::class.java)
            (context as Activity).startActivity(intent)
        }
    }
}