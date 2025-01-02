package com.example.assignment2_studentsapp.adapter

import android.content.Intent
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2_studentsapp.R
import com.example.assignment2_studentsapp.StudentsDetailsActivity
import com.example.assignment2_studentsapp.model.Student


class StudentViewHolder(itemView: View,
) : RecyclerView.ViewHolder(itemView) {
    private val studentName: TextView = itemView.findViewById(R.id.student_row_name_text_view)
    private val studentId: TextView = itemView.findViewById(R.id.student_row_id_text_view)
    private val studentPhone: TextView = itemView.findViewById(R.id.student_row_phone_text_view)
    private val studentAddress: TextView = itemView.findViewById(R.id.student_row_address_text_view)
    private val studentCheckBox: CheckBox = itemView.findViewById(R.id.student_row_check_box)
    private var student: Student? = null


    init {
        studentCheckBox.setOnClickListener() { view ->
            student?.isChecked = (view as CheckBox).isChecked
        }

        itemView.setOnClickListener() {
            student?.let {
                val context = itemView.context
                val intent = Intent(context, StudentsDetailsActivity::class.java).apply {
                    putExtra("student_name", it.name)
                    putExtra("student_id", it.id)
                    putExtra("student_phone", it.phone)
                    putExtra("student_address", it.address)
                    putExtra("student_isChecked", it.isChecked)
                }
                context.startActivity(intent)
            }
        }


    }

    fun bind(student: Student, position: Int) {
        studentName.text = student.name
        studentId.text = student.id
        studentPhone.text = student.phone
        studentAddress.text = student.address
        studentCheckBox.isChecked = student.isChecked
        studentCheckBox.tag = position
    }

}