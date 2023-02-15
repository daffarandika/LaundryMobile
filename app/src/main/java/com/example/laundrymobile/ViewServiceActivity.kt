package com.example.laundrymobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laundrymobile.adapter.LaundryServiceAdapter
import com.example.laundrymobile.databinding.ActivityViewServiceBinding
import com.example.laundrymobile.model.LaundryService
import com.example.laundrymobile.utils.Consts
import org.json.JSONArray

class ViewServiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewServiceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityViewServiceBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val services = mutableListOf<LaundryService>()
        val jsonArray = JSONArray(Consts.services)
        for (i in 0 until jsonArray.length()){
            val service = jsonArray.getJSONObject(i)
            services.add(LaundryService(
                name = service.getString("name"),
                qty = service.getString("qty"),
                price = service.getString("price")
            ))
        }
        binding.rec2.apply {
            this.adapter = LaundryServiceAdapter(this@ViewServiceActivity, services)
            this.layoutManager = LinearLayoutManager(this@ViewServiceActivity)
        }
    }
}