package com.example.studentsapp

data class Student(
    val id: String,
    var name: String,
    var phone: String,
    var address: String,
    var isChecked: Boolean = false
)
