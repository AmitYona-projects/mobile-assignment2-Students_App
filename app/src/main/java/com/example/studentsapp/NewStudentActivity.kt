package com.example.studentsapp

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.databinding.ActivityNewStudentBinding

class NewStudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.new_student)

        binding.studentImage.setImageResource(R.drawable.student_pic)

        binding.buttonSave.setOnClickListener {
            saveStudent()
        }

        binding.buttonCancel.setOnClickListener {
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveStudent() {
        val name = binding.editTextName.text.toString().trim()
        val id = binding.editTextId.text.toString().trim()
        val phone = binding.editTextPhone.text.toString().trim()
        val address = binding.editTextAddress.text.toString().trim()
        val isChecked = binding.checkBox.isChecked

        if (name.isEmpty() || id.isEmpty()) {
            Toast.makeText(this, "Name and ID are required", Toast.LENGTH_SHORT).show()
            return
        }

        // Check if student with this ID already exists
        if (StudentsRepository.getStudentById(id) != null) {
            Toast.makeText(this, "Student with this ID already exists", Toast.LENGTH_SHORT).show()
            return
        }

        val student = Student(
            id = id,
            name = name,
            phone = phone,
            address = address,
            isChecked = isChecked
        )

        StudentsRepository.addStudent(student)
        Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show()
        finish()
    }
}
