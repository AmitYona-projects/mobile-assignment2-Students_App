package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentsapp.databinding.ActivityStudentsListBinding

class StudentsListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStudentsListBinding
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.title = getString(R.string.students_list)

        setupRecyclerView()
        setupFab()
    }

    override fun onResume() {
        super.onResume()
        refreshRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = StudentAdapter(
            students = StudentsRepository.getAllStudents(),
            onItemClick = { student ->
                val intent = Intent(this, StudentDetailsActivity::class.java)
                intent.putExtra(Constants.EXTRA_STUDENT_ID, student.id)
                startActivity(intent)
            },
            onCheckboxClick = { student ->
                StudentsRepository.toggleCheckStatus(student.id)
                refreshRecyclerView()
            }
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun setupFab() {
        binding.fabAddStudent.setOnClickListener {
            val intent = Intent(this, NewStudentActivity::class.java)
            startActivity(intent)
        }
    }

    private fun refreshRecyclerView() {
        val students = StudentsRepository.getAllStudents()
        adapter = StudentAdapter(
            students = students,
            onItemClick = { student ->
                val intent = Intent(this, StudentDetailsActivity::class.java)
                intent.putExtra(Constants.EXTRA_STUDENT_ID, student.id)
                startActivity(intent)
            },
            onCheckboxClick = { student ->
                StudentsRepository.toggleCheckStatus(student.id)
                refreshRecyclerView()
            }
        )
        binding.recyclerView.adapter = adapter
    }
}
