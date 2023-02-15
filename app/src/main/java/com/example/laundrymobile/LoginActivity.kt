package com.example.laundrymobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.text.htmlEncode
import com.example.laundrymobile.databinding.ActivityLoginBinding
import com.example.laundrymobile.utils.Consts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class LoginActivity : AppCompatActivity() {
    private val TAG = "LoginActivity"
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
            val name = binding.etName.text.toString()
            val phone = "+62" + binding.etPhone.text.toString()
            login(name, phone)
        }
    }

    private fun login(name: String, phone: String) = runBlocking{
        launch(Dispatchers.IO) {
            val jsonObject = JSONObject()
            jsonObject.put("name", name)
            jsonObject.put("phone", phone)
            val conn = URL(Consts.url + "/api/logincustomer").openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.doInput = true
            conn.doOutput = true
            conn.setRequestProperty("Content-type", "application/json")
            val outputStreamWriter = OutputStreamWriter(conn.outputStream)
            outputStreamWriter.write(jsonObject.toString())
            outputStreamWriter.flush()
            if (conn.responseCode == 200){
                Consts.token = conn.inputStream.bufferedReader().readText()
                val phone_formatted = phone.replace("+", "")
                val conn2 = URL(Consts.url + "/api/customer?name=${name}&phone=%2B${phone_formatted}").openConnection() as HttpURLConnection
                conn2.requestMethod = "GET"
                conn2.setRequestProperty("token", Consts.token)
                conn2.setRequestProperty("Accept", "text/plain")
                conn2.setRequestProperty("Content-type", "text/plain; charset=utf-8")
                if (conn2.responseCode != 200) {
                    Log.d(TAG, "token: ${Consts.token}")
                    Log.d(TAG, "login: ${conn2.responseCode}")
                }
                Consts.user_id = conn2.inputStream.bufferedReader().readText()
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                this@LoginActivity.startActivity(intent)
                finish()
            } else {
                runOnUiThread{
                    Toast.makeText(this@LoginActivity, "Failed to login", Toast.LENGTH_SHORT).show()
                }
                Log.d(TAG, "login: ${Consts.token}")
            }
        }
    }
}