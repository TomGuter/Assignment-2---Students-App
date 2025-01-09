package com.example.assignment2_studentsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding

class StudentsDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students_details)

        window.statusBarColor = ContextCompat.getColor(this, android.R.color.white)

        val mainLayout = findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(mainLayout) { view, insets ->
            val systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(top = systemBarsInsets.top)
            insets
        }

        val studentName = intent.getStringExtra("STUDENT_NAME") ?: ""
        val studentId = intent.getStringExtra("STUDENT_ID") ?: ""
        val studentPhone = intent.getStringExtra("STUDENT_PHONE") ?: ""
        val studentAddress = intent.getStringExtra("STUDENT_ADDRESS") ?: ""
        val studentIsChecked = intent.getBooleanExtra("STUDENT_IS_CHECKED", false)

        findViewById<TextView>(R.id.student_name).text = "Name: $studentName"
        findViewById<TextView>(R.id.student_id).text = "ID: $studentId"
        findViewById<TextView>(R.id.student_phone).text = "Phone: $studentPhone"
        findViewById<TextView>(R.id.student_address).text = "Address: $studentAddress"
        findViewById<CheckBox>(R.id.student_is_checked).apply {
            isChecked = studentIsChecked
            isEnabled = false
        }

        findViewById<TextView>(R.id.toolbar_title).apply {
            text = "<Student Details"
            setOnClickListener {
                startActivity(Intent(this@StudentsDetailsActivity, StudentsRecyclerViewActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                })
            }
        }

        findViewById<Button>(R.id.edit_button).setOnClickListener {
            startActivity(Intent(this, EditStudentsActivity::class.java).apply {
                putExtra("STUDENT_NAME", studentName)
                putExtra("STUDENT_ID", studentId)
                putExtra("STUDENT_PHONE", studentPhone)
                putExtra("STUDENT_ADDRESS", studentAddress)
                putExtra("STUDENT_IS_CHECKED", studentIsChecked)
            })
        }
    }
}