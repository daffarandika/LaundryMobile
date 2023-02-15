package com.example.laundrymobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laundrymobile.adapter.DepositAdapter
import com.example.laundrymobile.databinding.ActivityMainBinding
import com.example.laundrymobile.model.Deposit
import com.example.laundrymobile.utils.Consts
import com.example.laundrymobile.utils.Helper
import com.example.laundrymobile.utils.formatAsDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor
import java.util.*
import kotlin.time.Duration

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val deposits = mutableListOf<Deposit>()
    var input = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getInput()
        val jsonArray = JSONArray(input)
        for (i in 0 until jsonArray.length()){
            val deposit = jsonArray.getJSONObject(i)
            deposits.add(Deposit(
                customer = deposit.getString("customer"),
                employee = deposit.getString("employee"),
                transactionDateTime = Helper.formatAsDateString(deposit.getString("transactionDateTime")),
                completedDateTime = deposit.getString("completedDateTime"),
                services = deposit.getString("services"),
            ))
        }
        val adapter = DepositAdapter(this@MainActivity, deposits)
        binding.rec.apply {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun getInput() = runBlocking{
        launch(Dispatchers.IO) {
            val conn = URL(Consts.url + "/api/Deposit?customerid=${Consts.user_id}").openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            conn.setRequestProperty("Content-type", "application/json")
            conn.setRequestProperty("token", Consts.token)
            input = conn.inputStream.bufferedReader().readText()
        }
    }
}