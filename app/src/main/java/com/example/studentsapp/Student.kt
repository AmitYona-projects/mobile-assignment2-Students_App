package com.example.studentsapp

data class Student(
    val id: String,
    val name: String,
    val phone: String,
    val address: String,
    val isChecked: Boolean = false
)
