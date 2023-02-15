package com.example.laundrymobile.utils

import android.annotation.SuppressLint
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

class Helper {
    companion object{
        fun formatAsDateString(input: String): String{
            val dfs = DateFormatSymbols()
            val months = dfs.months
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val initDate: Date = format.parse(input) as Date
            val calendar = Calendar.getInstance()
            calendar.time = initDate
            val date = calendar.get(Calendar.DATE)
            val month = months[calendar.get(Calendar.MONTH)]
            val year = calendar.get(Calendar.YEAR)
            return "$date $month $year"
        }
    }
}