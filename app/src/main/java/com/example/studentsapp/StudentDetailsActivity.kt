package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.databinding.ActivityStudentDetailsBinding

class StudentDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentDetailsBinding
    private var studentId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.student_details)

        studentId = intent.getStringExtra("STUDENT_ID")
        loadStudentDetails()

        binding.buttonEdit.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("STUDENT_ID", studentId)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh details when returning to this activity
        loadStudentDetails()
    }

    private fun loadStudentDetails() {
        studentId?.let { id ->
            val student = StudentsRepository.getStudentById(id)
            student?.let {
                binding.studentImage.setImageResource(R.drawable.student_pic)
                binding.textViewName.text = "name: ${it.name}"
                binding.textViewId.text = "id: ${it.id}"
                binding.textViewPhone.text = "phone: ${it.phone}"
                binding.textViewAddress.text = "address: ${it.address}"
                binding.checkBox.isChecked = it.isChecked
            }
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
}
