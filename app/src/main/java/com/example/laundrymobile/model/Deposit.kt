package com.example.laundrymobile.model

data class Deposit(
    val customer: String,
    val employee: String,
    val transactionDateTime: String,
    val completedDateTime: String,
    val services: String
)
