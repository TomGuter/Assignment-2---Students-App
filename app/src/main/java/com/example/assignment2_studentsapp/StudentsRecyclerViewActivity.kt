package com.example.assignment2_studentsapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2_studentsapp.model.Student
import com.example.assignment2_studentsapp.adapter.StudentsRecyclerAdapter
import com.example.assignment2_studentsapp.model.StudentRepository


class StudentsRecyclerViewActivity : AppCompatActivity() {

    private lateinit var students: MutableList<Student>

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_students_recycler_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<TextView>(R.id.toolbar_title).text = "Students List"
        students = StudentRepository.getStudents()


        findViewById<RecyclerView>(R.id.students_list_activity_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@StudentsRecyclerViewActivity)
            adapter = StudentsRecyclerAdapter(students)
        }

        addStudentButton()

    }

    private fun addStudentButton() {
        findViewById<View>(R.id.add_new_student_button).setOnClickListener() {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
        }

    }


}



