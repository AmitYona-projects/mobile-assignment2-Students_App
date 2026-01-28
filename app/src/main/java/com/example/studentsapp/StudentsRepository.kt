package com.example.studentsapp

object StudentsRepository {
    private val students = mutableListOf<Student>()

    fun getAllStudents(): List<Student> {
        return students.toList()
    }

    fun getStudentById(id: String): Student? {
        return students.find { it.id == id }
    }

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun updateStudent(oldId: String, updatedStudent: Student) {
        val index = students.indexOfFirst { it.id == oldId }
        if (index != -1) {
            students[index] = updatedStudent
        }
    }

    fun deleteStudent(id: String) {
        students.removeAll { it.id == id }
    }

    fun toggleCheckStatus(id: String) {
        val student = students.find { it.id == id }
        student?.isChecked = !(student?.isChecked ?: false)
    }
}
