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
            // If ID changed, remove old entry and add new one
            if (oldId != updatedStudent.id) {
                students.removeAt(index)
                students.add(updatedStudent)
            } else {
                students[index] = updatedStudent
            }
        }
    }

    fun deleteStudent(id: String) {
        students.removeAll { it.id == id }
    }

    fun toggleCheckStatus(id: String) {
        val student = students.find { it.id == id }
        student?.let {
            val index = students.indexOf(it)
            students[index] = it.copy(isChecked = !it.isChecked)
        }
    }
}
