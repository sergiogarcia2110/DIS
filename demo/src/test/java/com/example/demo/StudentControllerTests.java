package com.example.demo;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import com.google.gson.Gson;

import static org.junit.Assert.*;

public class StudentControllerTests {

    @Test
    public void testAddStudent() {
        StudentControllerMock controller = new StudentControllerMock();

        Student newStudent = new Student();
        newStudent.setFirstName("John");
        newStudent.setLastName("Doe");

        String studentJson = new Gson().toJson(newStudent);
        ResponseEntity<?> response = controller.addStudent(studentJson);

        assertEquals(200, response.getStatusCode().value());

        String studentsJson = controller.getStudents();
        Student[] students = new Gson().fromJson(studentsJson, Student[].class);

        assertTrue(students.length == 1);
        assertEquals("John", students[0].getFirstName());
        assertEquals("Doe", students[0].getLastName());
    }
}
