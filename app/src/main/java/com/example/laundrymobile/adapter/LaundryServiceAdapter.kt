package com.example.laundrymobile.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.laundrymobile.R
import com.example.laundrymobile.model.LaundryService

class LaundryServiceAdapter(
    val context: Context,
    val services: MutableList<LaundryService>
): RecyclerView.Adapter<LaundryServiceAdapter.SViewHolder>() {
    inner class SViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvQty: TextView = view.findViewById(R.id.tvQty)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.service_row_item, parent, false)
        return SViewHolder(view)
    }

    override fun getItemCount(): Int {
        return services.size
    }

    override fun onBindViewHolder(holder: SViewHolder, position: Int) {
        val service = services[position]
        holder.tvName.text = "Name : ${service.name}"
        holder.tvQty.text = "Quantity : ${service.qty}"
        holder.tvPrice.text = "Price : ${service.price}"
    }
}