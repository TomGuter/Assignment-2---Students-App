package com.example.assignment2_studentsapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment2_studentsapp.model.Student
import com.example.assignment2_studentsapp.model.StudentRepository


class EditStudentsActivity : AppCompatActivity() {

    private val students = StudentRepository.getStudents()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_students)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val studentNameField: EditText = findViewById(R.id.student_name)
        val studentIdField: EditText = findViewById(R.id.student_id)
        val studentPhoneField: EditText = findViewById(R.id.student_phone)
        val studentAddressField: EditText = findViewById(R.id.student_address)
        val studentIsCheckedField: CheckBox = findViewById(R.id.student_is_checked)
        val backToStudentListTextView: TextView = findViewById(R.id.toolbar_title)
        backToStudentListTextView.text = "<Edit Student"



        val studentName = intent.getStringExtra("STUDENT_NAME")
        val studentId = intent.getStringExtra("STUDENT_ID")
        val studentPhone = intent.getStringExtra("STUDENT_PHONE")
        val studentAddress = intent.getStringExtra("STUDENT_ADDRESS")
        val studentIsChecked = intent.getBooleanExtra("STUDENT_IS_CHECKED", false)



        studentNameField.setText(studentName)
        studentIdField.setText(studentId)
        studentPhoneField.setText(studentPhone)
        studentAddressField.setText(studentAddress)
        studentIsCheckedField.isChecked = studentIsChecked

        val cancelButton: Button = findViewById(R.id.cancel_button)
        val deleteButton: Button = findViewById(R.id.delete_button)
        val saveButton: Button = findViewById(R.id.save_button)

        cancelButton.setOnClickListener {
            finish()
        }


        deleteButton.setOnClickListener {
            StudentRepository.deleteStudent(studentId.toString())

            val intent = Intent(this, StudentsRecyclerViewActivity::class.java)
            startActivity(intent)
            finish()
        }

        saveButton.setOnClickListener {
            val updatedStudent = Student(
                studentNameField.text.toString(),
                studentIdField.text.toString(),
                studentPhoneField.text.toString(),
                studentAddressField.text.toString(),
                studentIsCheckedField.isChecked
            )

            StudentRepository.updateStudent(updatedStudent, studentId.toString())


            val intent = Intent(this, StudentsRecyclerViewActivity::class.java)
            startActivity(intent)
            finish()
        }



        backToStudentListTextView.setOnClickListener {
            val intent = Intent(this, StudentsDetailsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(intent)
        }
    }

}