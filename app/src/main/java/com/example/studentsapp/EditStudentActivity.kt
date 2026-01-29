package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.databinding.ActivityEditStudentBinding

class EditStudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditStudentBinding
    private var studentId: String? = null
    private var originalStudent: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.edit_student)

        studentId = intent.getStringExtra("STUDENT_ID")
        loadStudentData()

        binding.studentImage.setImageResource(R.drawable.student_pic)

        binding.buttonSave.setOnClickListener {
            saveStudent()
        }

        binding.buttonCancel.setOnClickListener {
            finish()
        }

        binding.buttonDelete.setOnClickListener {
            deleteStudent()
        }
    }

    private fun loadStudentData() {
        studentId?.let { id ->
            originalStudent = StudentsRepository.getStudentById(id)
            originalStudent?.let { student ->
                binding.editTextName.setText(student.name)
                binding.editTextId.setText(student.id)
                binding.editTextPhone.setText(student.phone)
                binding.editTextAddress.setText(student.address)
                binding.checkBox.isChecked = student.isChecked
            }
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

        originalStudent?.let { oldStudent ->
            if (oldStudent.id != id && StudentsRepository.getStudentById(id) != null) {
                Toast.makeText(this, "Student with this ID already exists", Toast.LENGTH_SHORT).show()
                return
            }

            val updatedStudent = Student(
                id = id,
                name = name,
                phone = phone,
                address = address,
                isChecked = isChecked
            )

            StudentsRepository.updateStudent(oldStudent.id, updatedStudent)
            Toast.makeText(this, "Student updated successfully", Toast.LENGTH_SHORT).show()
            
            if (oldStudent.id != id) {
                val intent = Intent(this, StudentsListActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            } else {
                finish()
            }
        }
    }

    private fun deleteStudent() {
        AlertDialog.Builder(this)
            .setTitle("Delete Student")
            .setMessage("Are you sure you want to delete this student?")
            .setPositiveButton("Delete") { _, _ ->
                studentId?.let { id ->
                    StudentsRepository.deleteStudent(id)
                    Toast.makeText(this, "Student deleted successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, StudentsListActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
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
}
