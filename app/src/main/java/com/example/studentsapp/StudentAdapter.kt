package com.example.studentsapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.databinding.ItemStudentBinding

class StudentAdapter(
    private val students: List<Student>,
    private val onItemClick: (Student) -> Unit,
    private val onCheckboxClick: (Student) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(
        private val binding: ItemStudentBinding,
        private val onItemClick: (Student) -> Unit,
        private val onCheckboxClick: (Student) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(student: Student) {
            binding.apply {
                studentImage.setImageResource(R.drawable.student_pic)
                studentName.text = student.name
                studentId.text = student.id
                checkBox.isChecked = student.isChecked

                checkBox.setOnClickListener {
                    onCheckboxClick(student)
                }

                root.setOnClickListener {
                    onItemClick(student)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = ItemStudentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StudentViewHolder(binding, onItemClick, onCheckboxClick)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(students[position])
    }

    override fun getItemCount(): Int = students.size
}
