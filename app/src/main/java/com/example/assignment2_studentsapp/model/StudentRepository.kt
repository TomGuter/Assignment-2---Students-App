package com.example.assignment2_studentsapp.model


object StudentRepository {
    private val students: MutableList<Student> = ArrayList()

    init {
        students.add(Student("John Doe", "123", "1234567890", "123 Main St"))
        students.add(Student("Jane Doe", "456", "0987654321", "456 Elm St"))
    }

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun getStudents(): MutableList<Student> {
        return students
    }

    fun removeStudent(student: Student) {
        students.remove(student)
    }

    fun updateStudent(student: Student) {
        val index = students.indexOfFirst { it.id == student.id }
        if (index != -1) {
            students[index] = student
        }
    }
}