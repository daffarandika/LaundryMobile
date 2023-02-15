package com.example.laundrymobile.utils

import java.text.SimpleDateFormat

fun String.formatAsDate(): String{
    return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(this).toString()
}