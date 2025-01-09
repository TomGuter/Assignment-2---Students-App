package com.example.assignment2_studentsapp.model


object StudentRepository {
    private val students: MutableList<Student> = ArrayList()

    init {
        students.add(Student("John Doe", "123", "1234567890", "123 Main St"))
        students.add(Student("Jane Doe", "456", "0987654321", "456 Main St"))
        students.add(Student("John Smith", "789", "1234567890", "789 Main St"))
        students.add(Student("Jane Smith", "101", "0987654321", "101 Main St"))
        students.add(Student("John Doe", "123", "1234567890", "123 Main St"))
        students.add(Student("Jane Doe", "456", "0987654321", "456 Main St"))
        students.add(Student("John Smith", "789", "1234567890", "789 Main St"))

    }

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun deleteStudent(studentId: String) {
        students.removeAll { student -> student.id == studentId }
    }

    fun updateStudent(student: Student, studentId: String) {
        student.id?.let { id ->
            val existingStudent = students.find { it.id == studentId }
            if (existingStudent != null) {
                existingStudent.name = student.name
                existingStudent.id = student.id
                existingStudent.phone = student.phone
                existingStudent.address = student.address
                existingStudent.isChecked = student.isChecked
            }
        }
    }

    fun getStudents(): MutableList<Student> {
        return students
    }
}